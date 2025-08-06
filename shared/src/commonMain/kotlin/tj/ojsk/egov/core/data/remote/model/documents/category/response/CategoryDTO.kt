package tj.ojsk.egov.core.data.remote.model.documents.category.response

import tj.ojsk.egov.core.domain.model.TextTranslations
import kotlinx.serialization.Serializable
import tj.ojsk.egov.core.data.remote.model.TextTranslationsDTO

@Serializable
data class CategoryDTO(
    val id: Int,
    val position: Int ?= null,
    val title: TextTranslationsDTO,
    val description: TextTranslationsDTO,
    val icon_id: String ?= null,
    val gradient_start_color: String ?= null,
    val gradient_end_color: String ?= null,
    val soon: Boolean,
    val icon: String ?= null,
    val sub_categories: List<CategoryDTO> ?= null
)