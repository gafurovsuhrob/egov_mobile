package tj.ojsk.egov.core.domain.repository.preload

interface PreloadRepository {
    suspend fun loadInitialData()
}