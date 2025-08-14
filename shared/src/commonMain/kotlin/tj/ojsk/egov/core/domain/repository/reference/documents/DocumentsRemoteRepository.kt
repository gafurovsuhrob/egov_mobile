package tj.ojsk.egov.core.domain.repository.reference.documents

import tj.ojsk.egov.core.domain.model.reference.documents.Document
import tj.ojsk.egov.core.domain.model.reference.documents.DocumentsData
import tj.ojsk.egov.core.domain.model.reference.documents.full.DocumentDataFull

interface DocumentsRemoteRepository {
    suspend fun getDocumentsData(): DocumentsData
    suspend fun getDocumentsByCategoryId(categoryId: Int): List<Document>
    suspend fun getDocumentById(parentId: Int): List<Document>
    suspend fun getDocumentDataFull(): DocumentDataFull
}