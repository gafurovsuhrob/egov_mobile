package tj.ojsk.egov.core.domain.model.reference.documents.full

data class DocumentFullResponse(
    val data: DocumentDataFull,
    val status_code: Int,
    val status_message: String
)