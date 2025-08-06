package tj.ojsk.egov.core.data.remote.model.auth.response
import kotlinx.serialization.Serializable

@Serializable
data class UserDataDTO(
    val has_access: Boolean,
    val user_type: String,
    val user: UserDTO,
    val legal_entity: LegalEntityDTO ?= null,
    val entrepreneur: EntrepreneurDTO ?= null,
    val token: TokenDTO,
    val organization: OrganizationDTO ?= null
)