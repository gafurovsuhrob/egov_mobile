package tj.ojsk.egov.core.domain.model

data class User(
    val birth_date: String,
    val email: String,
    val firstname: String,
    val gender: String,
    val id: Int,
    val lastname: String,
    val middlename: String,
    val mobile_phone: String,
    val passport_serial_number: String,
    val pinfl: String,
    val pport_issue_place: String,
    val registered_at: String,
    val tin: Int,
    val username: String
)