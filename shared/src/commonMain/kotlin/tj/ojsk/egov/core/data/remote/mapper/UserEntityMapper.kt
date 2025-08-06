package tj.ojsk.egov.core.data.remote.mapper

import database.UserEntity
import tj.ojsk.egov.core.data.remote.model.auth.response.UserDTO
import tj.ojsk.egov.core.domain.model.auth.User

fun User.toEntity(): UserEntity = UserEntity(
    id = id.toLong(),
    tin = tin.toString(),
    pinfl = pinfl,
    username = username,
    firstname = firstname,
    lastname = lastname,
    middlename = middlename,
    registered_at = registered_at,
    birth_date = birth_date,
    gender = gender,
    passport_serial_number = passport_serial_number,
    pport_issue_place = pport_issue_place,
    mobile_phone = mobile_phone,
    email = email
)

fun UserEntity.toDomain(): User = User(
    id = id.toInt(),
    tin = tin?.toInt() ?: 0,
    pinfl = pinfl ?: "",
    username = username ?: "",
    firstname = firstname ?: "",
    lastname = lastname ?: "",
    middlename = middlename ?: "",
    registered_at = registered_at ?: "",
    birth_date = birth_date ?: "",
    gender = gender ?: "",
    passport_serial_number = passport_serial_number ?: "",
    pport_issue_place = pport_issue_place ?: "",
    mobile_phone = mobile_phone ?: "",
    email = email ?: ""
)


fun UserEntity.toDTO(): UserDTO {
    return UserDTO(
        id = id.toInt(),
        tin = tin?.toInt() ?: 0,
        pinfl = pinfl ?: "",
        username = username ?: "",
        firstname = firstname ?: "",
        lastname = lastname ?: "",
        middlename = middlename ?: "",
        registered_at = registered_at ?: "",
        birth_date = birth_date ?: "",
        gender = gender ?: "",
        passport_serial_number = passport_serial_number ?: "",
        pport_issue_place = pport_issue_place ?: "",
        mobile_phone = mobile_phone ?: "",
        email = email ?: ""
    )
}