package tj.ojsk.egov.core.domain.model

data class ResponseData(
    val entrepreneur: Any,
    val has_access: Boolean,
    val legal_entity: Any,
    val organization: Organization,
    val token: Token,
    val user: User,
    val user_type: String
)