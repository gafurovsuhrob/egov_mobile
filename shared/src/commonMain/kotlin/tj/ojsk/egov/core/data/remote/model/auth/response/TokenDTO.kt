package tj.ojsk.egov.core.data.remote.model.auth.response
import kotlinx.serialization.Serializable

@Serializable
data class TokenDTO(
    val access_token: String,
    val refresh_token: String,
    val expires_in: Long,
    val authorities: List<String>
)