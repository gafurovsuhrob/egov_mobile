package tj.ojsk.egov.core.data.remote.model.documents.documents.response

import kotlinx.serialization.Serializable

@Serializable
data class DocumentsResponseDTO(
    val data: DocumentsDataDTO,
    val status_code: Int,
    val status_message: String
)