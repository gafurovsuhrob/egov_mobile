package tj.ojsk.egov.core.data.remote.model.documents.documents.response

data class DocumentFullResponseDto(
    val data: DocumentFullDataDto,
    val status_code: Int,
    val status_message: String
)