package tj.ojsk.egov.core.domain.model.documents.documents

import tj.ojsk.egov.core.domain.model.TextTranslations


data class Document(
    val description: TextTranslations,
    val icon: String,
    val id: Int,
    val position: Int,
    val tags: String,
    val title: TextTranslations,
    val type: String,
    val type_title: TextTranslations
)