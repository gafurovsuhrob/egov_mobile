package tj.ojsk.egov.feature.auth.view_model


data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val number: String = "",
    val verificationCode: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isLoggedIn: Boolean = false,
    val loginMethod: LoginMethod = LoginMethod.USERNAME_PASSWORD
)