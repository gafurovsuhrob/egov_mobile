package tj.ojsk.egov.core.domain.model.reference.documents

import tj.ojsk.egov.core.domain.model.LocalizedText


data class Document(
    val description: LocalizedText,
    val icon: String,
    val id: Int,
    val position: Int,
    val tags: String,
    val title: LocalizedText,
    val type: String,
    val type_title: LocalizedText
)