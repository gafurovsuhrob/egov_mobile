package tj.ojsk.egov.core.data.repository.auth

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import tj.ojsk.egov.core.constants.AUTH_LOGIN
import tj.ojsk.egov.core.data.local.manager.TokenManager
import tj.ojsk.egov.core.data.local.manager.UserManager
import tj.ojsk.egov.core.data.local.setting.PreferenceManager
import tj.ojsk.egov.core.data.remote.NetworkClient
import tj.ojsk.egov.core.data.remote.NetworkResult
import tj.ojsk.egov.core.data.remote.mapper.toDto
import tj.ojsk.egov.core.data.remote.model.auth.request.AuthDTO
import tj.ojsk.egov.core.data.remote.model.auth.response.UserResponseDTO
import tj.ojsk.egov.core.domain.model.auth.AuthCredentials
import tj.ojsk.egov.core.domain.model.auth.UserResponse
import tj.ojsk.egov.core.domain.repository.auth.AuthRepository
import tj.ojsk.egov.feature.auth.view_model.AuthResult
import tj.ojsk.egov.feature.auth.view_model.AuthResult.*

class AuthRepositoryImpl(
    private val preferenceManager: PreferenceManager,
    private val networkClient: NetworkClient,
    private val tokenManager: TokenManager,
    private val userManager: UserManager
) : AuthRepository {

    override suspend fun isLoggedIn(): Boolean {
        return preferenceManager.getIsLoggedIn().first()
    }

    override suspend fun setLoggedIn(value: Boolean) {
        preferenceManager.setIsLoggedIn(value)
    }

    override fun observeLoginState(): Flow<Boolean> {
        return preferenceManager.getIsLoggedIn()
    }

    override suspend fun loginByUsername(username: String, password: String): AuthResult {
        val credentials = AuthCredentials(username, password)
        val response = networkClient.post<UserResponseDTO, AuthDTO>(
            endpoint = AUTH_LOGIN,
            body = credentials.toDto(),
            authorized = false
        )

        return when (response) {
            is NetworkResult.Success -> {
                val userResponse = response.data
                val userData = userResponse.data

                tokenManager.saveToken(userData.token)
                userManager.saveUser(userData.user)
                setLoggedIn(true)
                Success
            }
            is NetworkResult.HttpError -> {
                Error("Ошибка: ${response.code} ${response.message}")
            }
            is NetworkResult.NetworkError -> {
                Error("Сетевая ошибка: ${response.exception.message}")
            }

            is NetworkResult.Unauthorized -> {
                Error("Неверные учетные данные или истек срок действия токена")
            }
        }
    }

    override suspend fun loginWithImzoCode(code: String): AuthResult {
        val endpoint = "oauth/sso/access_token"
        val queryParams = mapOf("code" to code)

        return when (val result = networkClient.post<UserResponseDTO, Unit>(
            endpoint = endpoint,
            queryParams = queryParams,
            authorized = false
        )) {
            is NetworkResult.Success -> {
                val userResponse = result.data
                val userData = userResponse.data

                tokenManager.saveToken(userData.token)
                userManager.saveUser(userData.user)
                setLoggedIn(true)
                Success
            }

            is NetworkResult.HttpError ->
                Error(result.message ?: "Ошибка сервера")

            is NetworkResult.NetworkError ->
                Error(result.exception.message ?: "Ошибка сети")

            is NetworkResult.Unauthorized ->
                Error("Не авторизован")
        }
    }

    override suspend fun logout() {
        userManager.deleteUser()
        tokenManager.deleteToken()
        setLoggedIn(false)
    }
}