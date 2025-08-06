package tj.ojsk.egov.core.data.remote.model.documents.documents.response

import kotlinx.serialization.Serializable

@Serializable
data class DocumentsDataDTO(
    val documents: List<DocumentDTO>
)