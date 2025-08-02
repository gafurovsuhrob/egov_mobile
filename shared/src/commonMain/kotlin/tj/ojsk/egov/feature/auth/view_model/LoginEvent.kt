package tj.ojsk.egov.feature.auth.view_model

sealed class LoginEvent {
    data class OnUsernameChanged(val value: String) : LoginEvent()
    data class OnPasswordChanged(val value: String) : LoginEvent()
    data class OnPhoneNumberChanged(val value: String) : LoginEvent()
    data class OnVerificationCodeChanged(val value: String) : LoginEvent()
    data class OnLoginMethodSelected(val method: LoginMethod) : LoginEvent()
    object OnLoginClicked : LoginEvent()
    object Reset : LoginEvent()
}

