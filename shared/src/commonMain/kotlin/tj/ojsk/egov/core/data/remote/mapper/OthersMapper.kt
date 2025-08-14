package tj.ojsk.egov.core.data.remote.mapper

import tj.ojsk.egov.core.data.remote.model.LocalizedTextDTO
import tj.ojsk.egov.core.domain.model.LocalizedText

fun LocalizedTextDTO.toDomain(): LocalizedText{
    return LocalizedText(
        ru = ru,
        tj = tj,
        en = en
    )
}