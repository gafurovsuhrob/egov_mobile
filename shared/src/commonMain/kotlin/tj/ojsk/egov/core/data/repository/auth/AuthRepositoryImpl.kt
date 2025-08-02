package tj.ojsk.egov.core.data.repository.auth

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import tj.ojsk.egov.core.data.local.setting.PreferenceManager
import tj.ojsk.egov.core.domain.repository.auth.AuthRepository
import tj.ojsk.egov.feature.auth.view_model.AuthResult

class AuthRepositoryImpl(
    private val preferenceManager: PreferenceManager
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
        return if (username == "admin" && password == "password") {
            setLoggedIn(true)
            AuthResult.Success
        } else {
            AuthResult.Error("Неверный логин или пароль")
        }
    }

    override suspend fun loginByPhone(phone: String, code: String): AuthResult {
        // В будущем отправка кода и верификация
        return if (code == "1234") {
            setLoggedIn(true)
            AuthResult.Success
        } else {
            AuthResult.Error("Неверный код")
        }
    }
}