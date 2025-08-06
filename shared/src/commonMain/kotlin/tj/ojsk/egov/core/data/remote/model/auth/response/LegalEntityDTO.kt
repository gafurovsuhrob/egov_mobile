package tj.ojsk.egov.core.data.remote.model.auth.response

import tj.ojsk.egov.core.domain.model.TextTranslations
import kotlinx.serialization.Serializable
import tj.ojsk.egov.core.data.remote.model.TextTranslationsDTO

@Serializable
data class LegalEntityDTO(
    val address: String,
    val address_region: TextTranslationsDTO,
    val address_region_id: Int,
    val address_sub_region: TextTranslationsDTO,
    val address_sub_region_id: Int,
    val csdp: String,
    val name: String,
    val oked: String,
    val opf: TextTranslationsDTO,
    val opf_id: String,
    val phone: String,
    val registration_date: String,
    val registration_number: String,
    val tin: Int
)