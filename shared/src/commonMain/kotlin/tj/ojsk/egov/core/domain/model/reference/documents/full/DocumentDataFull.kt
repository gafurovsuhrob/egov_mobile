package tj.ojsk.egov.core.domain.model.reference.documents.full

import tj.ojsk.egov.core.domain.model.LocalizedText

data class DocumentDataFull(
    val additional_data: String,
    val applicant_type: LocalizedText,
    val applicant_types: String,
    val applicationTypes: List<String>,
    val automatic_give: Boolean,
    val category_id: Int,
    val category_parent_id: Int,
    val document: DocumentSmall,
    val document_date: String,
    val document_link: String,
    val document_number: String,
    val dynamic_price: Boolean,
    val expiry_date: Int,
    val organization: LocalizedText,
    val organization_parent: LocalizedText,
    val re_registration_price: String,
    val review_fast_price: String,
    val review_time: Int,
    val review_week_days: Boolean,
    val service_cost: String,
    val state_duty: String,
    val usageFee: Boolean
)