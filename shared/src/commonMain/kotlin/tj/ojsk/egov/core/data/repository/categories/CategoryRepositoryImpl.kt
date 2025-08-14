package tj.ojsk.egov.core.data.repository.categories


import tj.ojsk.egov.core.constants.BASE_URL
import tj.ojsk.egov.core.constants.DOCUMENTS_CATEGORIES
import tj.ojsk.egov.core.data.remote.NetworkClient
import tj.ojsk.egov.core.data.remote.NetworkResult
import tj.ojsk.egov.core.data.remote.mapper.toDomain
import tj.ojsk.egov.core.data.remote.model.documents.category.response.CategoriesResponseDTO
import tj.ojsk.egov.core.domain.model.reference.category.Category
import tj.ojsk.egov.core.domain.repository.reference.categories.CategoryRepository

class CategoryRepositoryImpl(
    private val networkClient: NetworkClient
) : CategoryRepository {

    override suspend fun getCategoryList(): List<Category> {
        return when (val result = networkClient.get<CategoriesResponseDTO>(DOCUMENTS_CATEGORIES)) {
            is NetworkResult.Success -> {
                result.data.data.categories.map { it.toDomain() }
            }
            is NetworkResult.HttpError -> {
                throw Exception("HTTP error: ${result.code}")
            }
            is NetworkResult.Unauthorized -> {
                throw Exception("Unauthorized")
            }
            is NetworkResult.NetworkError -> {
                throw result.exception
            }
        }
    }
}