package tj.ojsk.egov.core.data.remote.mapper

import tj.ojsk.egov.core.data.remote.model.documents.documents.response.DocumentDTO
import tj.ojsk.egov.core.data.remote.model.documents.documents.response.DocumentsDataDTO
import tj.ojsk.egov.core.data.remote.model.documents.documents.response.DocumentsResponseDTO
import tj.ojsk.egov.core.domain.model.documents.documents.Document
import tj.ojsk.egov.core.domain.model.documents.documents.DocumentsData
import tj.ojsk.egov.core.domain.model.documents.documents.DocumentsResponse

fun DocumentDTO.toDomain(): Document {
    return Document(
        description = description.toDomain(),
        icon = icon,
        id = id,
        position = position,
        tags = tags,
        title = title.toDomain(),
        type = type,
        type_title = type_title.toDomain(),
    )
}

fun DocumentsDataDTO.toDomain(): DocumentsData {
    return DocumentsData(
        documents = documents.map { it.toDomain() }
    )
}

fun DocumentsResponseDTO.toDomain(): DocumentsResponse {
    return DocumentsResponse(
        data = data.toDomain(),
        status_code = status_code,
        status_message = status_message
    )
}