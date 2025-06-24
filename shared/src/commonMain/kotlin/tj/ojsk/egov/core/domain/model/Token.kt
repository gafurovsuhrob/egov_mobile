package tj.ojsk.egov.core.domain.model

data class Token(
    val access_token: String,
    val authorities: List<Any>,
    val expires_in: Int,
    val refresh_token: String
)