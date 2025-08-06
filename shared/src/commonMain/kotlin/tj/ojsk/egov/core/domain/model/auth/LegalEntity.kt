package tj.ojsk.egov.core.domain.model.auth

import tj.ojsk.egov.core.domain.model.TextTranslations
import kotlinx.serialization.Serializable

data class LegalEntity(
    val address: String,
    val address_region: TextTranslations,
    val address_region_id: Int,
    val address_sub_region: TextTranslations,
    val address_sub_region_id: Int,
    val csdp: String,
    val name: String,
    val oked: String,
    val opf: TextTranslations,
    val opf_id: String,
    val phone: String,
    val registration_date: String,
    val registration_number: String,
    val tin: Int
)