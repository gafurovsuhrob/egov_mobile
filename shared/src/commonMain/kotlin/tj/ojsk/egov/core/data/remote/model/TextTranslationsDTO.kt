package tj.ojsk.egov.core.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class TextTranslationsDTO(
    val en: String,
    val ru: String,
    val tj: String
)