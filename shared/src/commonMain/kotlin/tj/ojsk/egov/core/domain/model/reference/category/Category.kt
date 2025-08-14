package tj.ojsk.egov.core.domain.model.reference.category

import tj.ojsk.egov.core.domain.model.LocalizedText

data class Category(
    val id: Int,
    val position: Int ?= null,
    val title: LocalizedText,
    val description: LocalizedText,
    val icon_id: String ?= null,
    val gradient_start_color: String ?= null,
    val gradient_end_color: String ?= null,
    val soon: Boolean,
    val icon: String ?= null,
    val sub_categories: List<Category> ?= null
)