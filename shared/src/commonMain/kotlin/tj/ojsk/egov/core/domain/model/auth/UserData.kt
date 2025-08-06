package tj.ojsk.egov.core.domain.model.auth
import kotlinx.serialization.Serializable

data class UserData(
    val has_access: Boolean,
    val user_type: String,
    val user: User,
    val legal_entity: LegalEntity ?= null,
    val entrepreneur: Entrepreneur ?= null,
    val token: Token,
    val organization: Organization ?= null
)