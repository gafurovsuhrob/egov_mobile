package tj.ojsk.egov.core.data.remote.mapper

import tj.ojsk.egov.core.data.remote.model.documents.category.response.CategoriesDataDTO
import tj.ojsk.egov.core.data.remote.model.documents.category.response.CategoriesResponseDTO
import tj.ojsk.egov.core.data.remote.model.documents.category.response.CategoryDTO
import tj.ojsk.egov.core.domain.model.reference.category.CategoriesData
import tj.ojsk.egov.core.domain.model.reference.category.CategoriesResponse
import tj.ojsk.egov.core.domain.model.reference.category.Category

fun CategoryDTO.toDomain(): Category {
    return Category(
        id = id,
        position = position,
        title = title.toDomain(),
        description = description.toDomain(),
        icon_id = icon_id,
        gradient_start_color = gradient_start_color,
        gradient_end_color = gradient_end_color,
        soon = soon,
    )
}

fun CategoriesDataDTO.toDomain(): CategoriesData {
    return CategoriesData(
        categories = categories.map { it.toDomain() }
    )
}

fun CategoriesResponseDTO.toDomain(): CategoriesResponse {
    return CategoriesResponse(
        data = data.toDomain(),
        status_code = status_code,
        status_message = status_message
    )
}