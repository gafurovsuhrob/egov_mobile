package tj.ojsk.egov.core.data.remote.model.auth.response

import kotlinx.serialization.Serializable

@Serializable
data class UserResponseDTO(
    val data: UserDataDTO,
    val status_code: Int,
    val status_message: String
)