package tj.ojsk.egov.core.domain.model.documents.category

import kotlinx.serialization.Serializable

data class CategoriesResponse(
    val data: CategoriesData,
    val status_code: Int,
    val status_message: String
)