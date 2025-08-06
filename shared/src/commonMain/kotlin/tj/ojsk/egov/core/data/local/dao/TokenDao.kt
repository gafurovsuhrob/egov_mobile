package tj.ojsk.egov.core.data.local.dao

import tj.ojsk.egov.core.data.remote.model.auth.response.TokenDTO

interface TokenDao {
    suspend fun saveToken(token: TokenDTO)
    suspend fun getToken(): TokenDTO?
    suspend fun deleteToken()
}