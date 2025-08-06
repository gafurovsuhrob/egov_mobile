package tj.ojsk.egov.core.domain.model.documents.documents


data class DocumentsResponse(
    val data: DocumentsData,
    val status_code: Int,
    val status_message: String
)