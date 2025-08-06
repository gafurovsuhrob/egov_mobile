package tj.ojsk.egov.core.data.remote.model.auth.response
import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(
    val id: Int,
    val tin: Int,
    val pinfl: String ?= null,
    val username: String,
    val firstname: String,
    val lastname: String,
    val middlename: String ?= null,
    val registered_at: String ?= null,
    val birth_date: String ?= null,
    val gender: String ?= null,
    val passport_serial_number: String ?= null,
    val pport_issue_place: String ?= null,
    val mobile_phone: String ?= null,
    val email: String ?= null
)

