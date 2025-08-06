package tj.ojsk.egov.core.data.remote.model.auth.response

import tj.ojsk.egov.core.domain.model.TextTranslations
import kotlinx.serialization.Serializable
import tj.ojsk.egov.core.data.remote.model.TextTranslationsDTO

@Serializable
data class OrganizationDTO(
    val organization_id: Int,
    val organization_name: TextTranslationsDTO
)