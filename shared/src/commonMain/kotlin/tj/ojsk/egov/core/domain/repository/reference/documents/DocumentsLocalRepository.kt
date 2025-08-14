package tj.ojsk.egov.core.domain.repository.reference.documents

import tj.ojsk.egov.core.domain.model.reference.documents.Document
import tj.ojsk.egov.core.domain.model.reference.documents.DocumentsData

interface DocumentsLocalRepository {
    suspend fun getDocuments(): DocumentsData
    suspend fun setDocuments(): DocumentsData
    suspend fun getDocumentsByCategoryId(categoryId: Int): List<Document>
    suspend fun getDocumentById(parentId: Int): List<Document>
}