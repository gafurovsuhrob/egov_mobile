package tj.ojsk.egov.core.domain.model.auth

import tj.ojsk.egov.core.domain.model.TextTranslations
import kotlinx.serialization.Serializable

data class Organization(
    val organization_id: Int,
    val organization_name: TextTranslations
)