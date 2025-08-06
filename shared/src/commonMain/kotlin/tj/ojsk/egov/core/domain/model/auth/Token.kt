package tj.ojsk.egov.core.domain.model.auth
import kotlinx.serialization.Serializable

data class Token(
    val access_token: String,
    val refresh_token: String,
    val expires_in: Long,
    val authorities: List<String>
)