package tj.ojsk.egov.core.data.remote.model.auth.response

import kotlinx.serialization.Serializable
import tj.ojsk.egov.core.data.remote.model.LocalizedTextDTO

@Serializable
data class OrganizationDTO(
    val organization_id: Int,
    val organization_name: LocalizedTextDTO
)