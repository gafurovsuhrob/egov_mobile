package tj.ojsk.egov.core.domain.repository.auth

interface TokenProvider {
    suspend fun getToken(): String?
}