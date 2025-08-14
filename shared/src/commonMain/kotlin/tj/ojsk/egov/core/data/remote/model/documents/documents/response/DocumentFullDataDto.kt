package tj.ojsk.egov.core.data.remote.model.documents.documents.response

import tj.ojsk.egov.core.data.remote.model.LocalizedTextDTO

data class DocumentFullDataDto(
    val additional_data: String,
    val applicant_type: LocalizedTextDTO,
    val applicant_types: String,
    val applicationTypes: List<String>,
    val automatic_give: Boolean,
    val category_id: Int,
    val category_parent_id: Int,
    val document: DocumentSmallDto,
    val document_date: String,
    val document_link: String,
    val document_number: String,
    val dynamic_price: Boolean,
    val expiry_date: Int,
    val organization: LocalizedTextDTO,
    val organization_parent: LocalizedTextDTO,
    val re_registration_price: String,
    val review_fast_price: String,
    val review_time: Int,
    val review_week_days: Boolean,
    val service_cost: String,
    val state_duty: String,
    val usageFee: Boolean
)