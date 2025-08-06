package tj.ojsk.egov.core.data.remote.model.auth.request

import kotlinx.serialization.Serializable

@Serializable
data class AuthDTO(
    val email: String,
    val password: String
)