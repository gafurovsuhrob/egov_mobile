package tj.ojsk.egov.core.data.remote.model.documents.documents.response

import tj.ojsk.egov.core.domain.model.TextTranslations

import kotlinx.serialization.Serializable
import tj.ojsk.egov.core.data.remote.model.TextTranslationsDTO

@Serializable
data class DocumentDTO(
    val description: TextTranslationsDTO,
    val icon: String,
    val id: Int,
    val position: Int,
    val tags: String,
    val title: TextTranslationsDTO,
    val type: String,
    val type_title: TextTranslationsDTO
)