package tj.ojsk.egov.core.data.local.manager

import tj.ojsk.egov.core.data.local.dao.TokenDao
import tj.ojsk.egov.core.data.remote.model.auth.response.TokenDTO

class TokenManager(
    private val tokenDao: TokenDao
) {

    suspend fun saveToken(token: TokenDTO) {
        tokenDao.saveToken(token)
    }

    suspend fun getAccessToken(): String? {
        return tokenDao.getToken()?.access_token
    }

    suspend fun getRefreshToken(): String? {
        return tokenDao.getToken()?.refresh_token
    }

    suspend fun getToken(): TokenDTO? {
        return tokenDao.getToken()
    }

    suspend fun deleteToken() {
        tokenDao.deleteToken()
    }

    suspend fun isTokenExpired(): Boolean {
        val token = tokenDao.getToken() ?: return true
        // Здесь можно добавить более точную проверку с текущим временем,
        // если в будущем появится поле `created_at` или `expires_at`
        return token.expires_in <= 0
    }
}