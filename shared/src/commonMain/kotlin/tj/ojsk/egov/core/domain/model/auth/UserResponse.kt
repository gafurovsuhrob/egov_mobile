package tj.ojsk.egov.core.domain.model.auth

import kotlinx.serialization.Serializable

data class UserResponse(
    val data: UserData,
    val status_code: Int,
    val status_message: String
)