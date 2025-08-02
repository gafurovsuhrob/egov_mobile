package tj.ojsk.egov.feature.auth.view_model

sealed class AuthResult {
    object Success : AuthResult()
    data class Error(val message: String) : AuthResult()
}