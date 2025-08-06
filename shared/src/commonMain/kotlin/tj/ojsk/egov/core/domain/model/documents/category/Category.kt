package tj.ojsk.egov.core.domain.model.documents.category

import tj.ojsk.egov.core.domain.model.TextTranslations
import kotlinx.serialization.Serializable

data class Category(
    val id: Int,
    val position: Int ?= null,
    val title: TextTranslations,
    val description: TextTranslations,
    val icon_id: String ?= null,
    val gradient_start_color: String ?= null,
    val gradient_end_color: String ?= null,
    val soon: Boolean,
    val icon: String ?= null,
    val sub_categories: List<Category> ?= null
)