package tj.ojsk.egov.core.data.remote.model.documents.documents.response

import tj.ojsk.egov.core.data.remote.model.LocalizedTextDTO

data class DocumentSmallDto(
    val icon: String,
    val id: Int,
    val title: LocalizedTextDTO,
    val type: String,
    val type_title: LocalizedTextDTO
)