package tj.ojsk.egov.core.domain.model.auth

import tj.ojsk.egov.core.domain.model.LocalizedText

data class Organization(
    val organization_id: Int,
    val organization_name: LocalizedText
)