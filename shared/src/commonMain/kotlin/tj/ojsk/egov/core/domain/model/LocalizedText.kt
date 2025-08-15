package tj.ojsk.egov.core.domain.model

import kotlinx.serialization.Serializable

@Serializable

data class LocalizedText(
    val en: String,
    val ru: String,
    val tj: String
)
