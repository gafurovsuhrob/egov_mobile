package tj.ojsk.egov.core.domain.repository.auth

import kotlinx.coroutines.flow.Flow
import tj.ojsk.egov.feature.auth.view_model.AuthResult

interface AuthRepository {
    suspend fun isLoggedIn(): Boolean
    suspend fun setLoggedIn(value: Boolean)
    fun observeLoginState(): Flow<Boolean>


    suspend fun loginByUsername(username: String, password: String): AuthResult
    suspend fun loginWithImzoCode(code: String): AuthResult

    suspend fun logout()
}