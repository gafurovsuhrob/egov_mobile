package tj.ojsk.egov.core.data.remote.mapper

import tj.ojsk.egov.core.data.remote.model.auth.request.AuthDTO
import tj.ojsk.egov.core.data.remote.model.auth.response.EntrepreneurDTO
import tj.ojsk.egov.core.data.remote.model.auth.response.LegalEntityDTO
import tj.ojsk.egov.core.data.remote.model.auth.response.OrganizationDTO
import tj.ojsk.egov.core.data.remote.model.auth.response.TokenDTO
import tj.ojsk.egov.core.data.remote.model.auth.response.UserDTO
import tj.ojsk.egov.core.data.remote.model.auth.response.UserDataDTO
import tj.ojsk.egov.core.data.remote.model.auth.response.UserResponseDTO
import tj.ojsk.egov.core.domain.model.auth.AuthCredentials
import tj.ojsk.egov.core.domain.model.auth.Entrepreneur
import tj.ojsk.egov.core.domain.model.auth.LegalEntity
import tj.ojsk.egov.core.domain.model.auth.Organization
import tj.ojsk.egov.core.domain.model.auth.Token
import tj.ojsk.egov.core.domain.model.auth.User
import tj.ojsk.egov.core.domain.model.auth.UserData
import tj.ojsk.egov.core.domain.model.auth.UserResponse

fun AuthCredentials.toDto(): AuthDTO {
    return AuthDTO(
        email = username,
        password = password
    )
}

fun EntrepreneurDTO.toDomain(): Entrepreneur{
    return Entrepreneur(
        activity_type = activity_type,
        address = address,
        address_region = address_region,
        address_sub_region = address_sub_region,
        address_village = address_village,
        name = name,
        opf = opf,
        phone = phone,
        pinfl = pinfl,
        registration_date = registration_date,
        registration_number = registration_number,
        tin = tin
    )
}

fun LegalEntityDTO.toDomain(): LegalEntity{
    return LegalEntity(
        address = address,
        address_region = address_region.toDomain(),
        address_region_id = address_region_id,
        address_sub_region = address_sub_region.toDomain(),
        address_sub_region_id = address_sub_region_id,
        csdp = csdp,
        name = name,
        oked = oked,
        opf = opf.toDomain(),
        opf_id = opf_id,
        phone = phone,
        registration_date = registration_date,
        registration_number = registration_number,
        tin = tin
    )
}

fun OrganizationDTO.toDomain(): Organization{
    return Organization(
        organization_id = organization_id,
        organization_name = organization_name.toDomain()
    )
}

fun TokenDTO.toDomain(): Token {
    return Token(
        access_token = access_token,
        refresh_token = refresh_token,
        expires_in = expires_in,
        authorities = authorities,
    )
}


fun UserDTO.toDomain(): User {
    return User(
        id = id,
        tin = tin,
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
}

fun UserDataDTO.toDomain(): UserData {
    return UserData(
        has_access = has_access,
        user_type = user_type,
        user = user.toDomain(),
        legal_entity = legal_entity?.toDomain(),
        entrepreneur = entrepreneur?.toDomain(),
        token = token.toDomain(),
        organization = organization?.toDomain()
    )
}

fun UserResponseDTO.toDomain(): UserResponse {
    return UserResponse(
        data = data.toDomain(),
        status_code = status_code,
        status_message = status_message
    )
}