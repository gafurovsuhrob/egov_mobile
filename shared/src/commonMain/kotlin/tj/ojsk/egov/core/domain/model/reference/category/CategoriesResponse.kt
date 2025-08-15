package tj.ojsk.egov.core.domain.model.reference.category


data class CategoriesResponse(
    val data: CategoriesData,
    val status_code: Int,
    val status_message: String
)