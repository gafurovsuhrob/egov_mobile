package tj.ojsk.egov.core.data.remote.mapper

import tj.ojsk.egov.core.data.remote.model.TextTranslationsDTO
import tj.ojsk.egov.core.domain.model.TextTranslations

fun TextTranslationsDTO.toDomain(): TextTranslations{
    return TextTranslations(
        ru = ru,
        tj = tj,
        en = en
    )
}