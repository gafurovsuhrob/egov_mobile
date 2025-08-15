package tj.ojsk.egov.core.domain.repository.reference.categories

import kotlinx.coroutines.flow.Flow
import tj.ojsk.egov.core.data.remote.NetworkResult
import tj.ojsk.egov.core.domain.model.reference.category.Category

interface CategoryRepository {
    suspend fun getCategories(): NetworkResult<List<Category>>
}