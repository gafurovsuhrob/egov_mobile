package tj.ojsk.egov.core.data.remote.model.documents.category.response

import kotlinx.serialization.Serializable

@Serializable
data class CategoriesResponseDTO(
    val data: CategoriesDataDTO,
    val status_code: Int,
    val status_message: String
)