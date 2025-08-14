package tj.ojsk.egov.core.domain.repository.reference.categories

import tj.ojsk.egov.core.domain.model.reference.category.Category

interface CategoryRepository {
    suspend fun getCategoryList(): List<Category>
}