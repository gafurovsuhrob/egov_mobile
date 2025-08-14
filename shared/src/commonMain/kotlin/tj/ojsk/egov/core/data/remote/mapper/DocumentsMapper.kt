package tj.ojsk.egov.core.data.remote.mapper

import tj.ojsk.egov.core.data.remote.model.documents.documents.response.DocumentDTO
import tj.ojsk.egov.core.data.remote.model.documents.documents.response.DocumentFullDataDto
import tj.ojsk.egov.core.data.remote.model.documents.documents.response.DocumentFullResponseDto
import tj.ojsk.egov.core.data.remote.model.documents.documents.response.DocumentSmallDto
import tj.ojsk.egov.core.data.remote.model.documents.documents.response.DocumentsDataDTO
import tj.ojsk.egov.core.data.remote.model.documents.documents.response.DocumentsResponseDTO
import tj.ojsk.egov.core.domain.model.reference.documents.Document
import tj.ojsk.egov.core.domain.model.reference.documents.full.DocumentDataFull
import tj.ojsk.egov.core.domain.model.reference.documents.full.DocumentFullResponse
import tj.ojsk.egov.core.domain.model.reference.documents.full.DocumentSmall
import tj.ojsk.egov.core.domain.model.reference.documents.DocumentsData
import tj.ojsk.egov.core.domain.model.reference.documents.DocumentsResponse

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

fun DocumentSmallDto.toDomain(): DocumentSmall{
    return DocumentSmall(
        icon = icon,
        id = id,
        title = title.toDomain(),
        type = type,
        type_title = type_title.toDomain(),
    )
}

fun DocumentFullDataDto.toDomain(): DocumentDataFull{
    return DocumentDataFull(
        additional_data = additional_data,
        applicant_type = applicant_type.toDomain(),
        applicant_types = applicant_types,
        applicationTypes = applicationTypes,
        automatic_give = automatic_give,
        category_id = category_id,
        category_parent_id = category_parent_id,
        document = document.toDomain(),
        document_date = document_date,
        document_link = document_link,
        document_number = document_number,
        dynamic_price = dynamic_price,
        expiry_date = expiry_date,
        organization = organization.toDomain(),
        organization_parent = organization_parent.toDomain(),
        re_registration_price = re_registration_price,
        review_fast_price = review_fast_price,
        review_time = review_time,
        review_week_days = review_week_days,
        service_cost = service_cost,
        state_duty = state_duty,
        usageFee = usageFee,
    )
}

fun DocumentFullResponseDto.toDomain(): DocumentFullResponse {
    return DocumentFullResponse(
        data = data.toDomain(),
        status_code = status_code,
        status_message = status_message
    )
}
