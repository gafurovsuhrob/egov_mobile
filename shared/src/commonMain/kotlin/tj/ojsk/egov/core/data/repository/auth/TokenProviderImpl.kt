package tj.ojsk.egov.core.data.repository.auth

import tj.ojsk.egov.core.data.local.dao.TokenDao
import tj.ojsk.egov.core.domain.repository.auth.TokenProvider

class TokenProviderImpl(
    private val tokenDao: TokenDao
) : TokenProvider {
    override suspend fun getToken(): String? {
        return tokenDao.getToken()?.access_token
    }
}