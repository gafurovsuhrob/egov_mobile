package tj.ojsk.egov.core.data.remote.model.documents.category.response

import kotlinx.serialization.Serializable

@Serializable
data class CategoriesDataDTO(
    val categories: List<CategoryDTO>
)