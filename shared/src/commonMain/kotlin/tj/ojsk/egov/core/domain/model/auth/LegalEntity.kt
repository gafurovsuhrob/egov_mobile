package tj.ojsk.egov.core.domain.model.auth

import tj.ojsk.egov.core.domain.model.LocalizedText

data class LegalEntity(
    val address: String,
    val address_region: LocalizedText,
    val address_region_id: Int,
    val address_sub_region: LocalizedText,
    val address_sub_region_id: Int,
    val csdp: String,
    val name: String,
    val oked: String,
    val opf: LocalizedText,
    val opf_id: String,
    val phone: String,
    val registration_date: String,
    val registration_number: String,
    val tin: Int
)