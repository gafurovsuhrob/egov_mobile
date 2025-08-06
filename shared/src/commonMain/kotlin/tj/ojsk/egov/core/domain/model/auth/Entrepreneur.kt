package tj.ojsk.egov.core.domain.model.auth

import kotlinx.serialization.Serializable

data class Entrepreneur(
    val activity_type: String,
    val address: String,
    val address_region: String,
    val address_sub_region: String,
    val address_village: String,
    val name: String,
    val opf: String,
    val phone: String,
    val pinfl: String,
    val registration_date: String,
    val registration_number: String,
    val tin: String
)