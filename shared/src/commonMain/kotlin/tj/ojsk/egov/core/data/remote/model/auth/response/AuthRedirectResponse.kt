package tj.ojsk.egov.core.data.remote.model.auth.response

import kotlinx.serialization.Serializable

@Serializable
data class AuthRedirectResponse(
    val code: Int,
    val message: String,
    val url: String
)