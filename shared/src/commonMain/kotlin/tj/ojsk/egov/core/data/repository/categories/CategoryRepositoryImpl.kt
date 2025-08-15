package tj.ojsk.egov.core.data.repository.categories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import tj.ojsk.egov.core.constants.DOCUMENTS_CATEGORIES
import tj.ojsk.egov.core.data.remote.NetworkClient
import tj.ojsk.egov.core.data.remote.NetworkResult
import tj.ojsk.egov.core.data.remote.mapper.toDomain
import tj.ojsk.egov.core.data.remote.model.documents.category.response.CategoriesResponseDTO
import tj.ojsk.egov.core.domain.model.reference.category.Category
import tj.ojsk.egov.core.domain.repository.reference.categories.CategoryRepository
import tj.ojsk.egov.feature.auth.view_model.AuthResult.Error
import tj.ojsk.egov.feature.auth.view_model.AuthResult.Success
import tj.ojsk.egov.platform.Logger

class CategoryRepositoryImpl(
    private val networkClient: NetworkClient
) : CategoryRepository {

    override suspend fun getCategories(): NetworkResult<List<Category>> {
        val response = networkClient.get<CategoriesResponseDTO>(
            endpoint = DOCUMENTS_CATEGORIES
        )

        return when (response) {
            is NetworkResult.Success -> {
                NetworkResult.Success(response.data.data.categories.map { it.toDomain() })
            }
            is NetworkResult.HttpError -> {
                NetworkResult.HttpError(response.code, response.message)
            }
            is NetworkResult.Unauthorized -> {
                NetworkResult.Unauthorized
            }
            is NetworkResult.NetworkError -> {
                NetworkResult.NetworkError(response.exception)
            }
        }
    }
}