package tj.ojsk.egov.core.domain.model.reference.documents


data class DocumentsResponse(
    val data: DocumentsData,
    val status_code: Int,
    val status_message: String
)