package tj.ojsk.egov.core.data.local.dao

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import tj.ojsk.egov.core.data.remote.model.auth.response.TokenDTO
import tj.ojsk.egov.database.EGOVDatabase

class TokenDaoImpl(private val db: EGOVDatabase) : TokenDao {
    override suspend fun saveToken(token: TokenDTO) {
        db.tokenQueries.deleteAll()
        db.tokenQueries.insertToken(
            access_token = token.access_token,
            refresh_token = token.refresh_token,
            expires_in = token.expires_in,
            authorities = Json.encodeToString(token.authorities)
        )
    }

    override suspend fun getToken(): TokenDTO? {
        return db.tokenQueries.selectToken().executeAsOneOrNull()?.let {
            TokenDTO(
                access_token = it.access_token,
                refresh_token = it.refresh_token,
                expires_in = it.expires_in,
                authorities = Json.decodeFromString(it.authorities)
            )
        }
    }

    override suspend fun deleteToken() {
        db.tokenQueries.deleteAll()
    }
}