package tj.ojsk.egov.core.domain.model.reference.documents.full

import tj.ojsk.egov.core.domain.model.LocalizedText

data class DocumentSmall(
    val icon: String,
    val id: Int,
    val title: LocalizedText,
    val type: String,
    val type_title: LocalizedText
)