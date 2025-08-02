package tj.ojsk.egov.feature.auth.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import tj.ojsk.egov.core.domain.repository.auth.AuthRepository
import tj.ojsk.egov.core.domain.repository.settings.SettingsRepository
import tj.ojsk.egov.platform.Logger

class AuthViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnUsernameChanged -> {
                _uiState.update {
                    val newState = it.copy(username = event.value)
                    if (it.errorMessage != null && event.value != it.username) {
                        newState.copy(errorMessage = null)
                    } else newState
                }
            }
            is LoginEvent.OnPasswordChanged -> {
                _uiState.update {
                    val newState = it.copy(password = event.value)
                    if (it.errorMessage != null && event.value != it.password) {
                        newState.copy(errorMessage = null)
                    } else newState
                }
            }
            is LoginEvent.OnPhoneNumberChanged -> {
                _uiState.update { it.copy(number = event.value, errorMessage = null) }
            }
            is LoginEvent.OnVerificationCodeChanged -> {
                _uiState.update { it.copy(verificationCode = event.value, errorMessage = null) }
            }
            is LoginEvent.OnLoginClicked -> {
                when (_uiState.value.loginMethod) {
                    LoginMethod.USERNAME_PASSWORD -> loginWithUsername()
                    LoginMethod.PHONE_CODE -> {}
                }
            }
            is LoginEvent.OnLoginMethodSelected -> {
                _uiState.update {
                    it.copy(loginMethod = event.method, errorMessage = null)
                }
            }
            is LoginEvent.Reset -> {
                _uiState.value = LoginUiState()
            }
        }
    }

    fun login() {
        viewModelScope.launch {
            authRepository.setLoggedIn(true)
        }
    }

    fun logout() {
        viewModelScope.launch {
            authRepository.setLoggedIn(false)
        }
    }

    val isAuthenticated = flow {
        emit(authRepository.isLoggedIn())
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)


    private fun loginWithUsername() {
        val username = _uiState.value.username
        val password = _uiState.value.password

        val validationError = LoginValidator.validateUsernamePassword(username, password)
        if (validationError != null) {
            _uiState.update { it.copy(errorMessage = validationError) }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                when (val result = authRepository.loginByUsername(username, password)) {
                    is AuthResult.Success -> {
                        _uiState.update { it.copy(isLoading = false, isLoggedIn = true) }
                        login()
                    }
                    is AuthResult.Error -> {
                        _uiState.update {
                            it.copy(isLoading = false, errorMessage = result.message)
                        }
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, errorMessage = e.message ?: "Неизвестная ошибка")
                }
            }
        }
    }

//    private fun loginIMZO() {
//        val phone = _uiState.value.number
//        val code = _uiState.value.verificationCode
//
//        val validationError = LoginValidator.validatePhoneAndCode(phone, code)
//        if (validationError != null) {
//            _uiState.update { it.copy(errorMessage = validationError) }
//            return
//        }
//
//        viewModelScope.launch {
//            _uiState.update { it.copy(isLoading = true) }
//            try {
//                val result = authRepository.loginByPhone(phone, code)
//                _uiState.update {
//                    it.copy(isLoading = false, isLoggedIn = result)
//                }
//            } catch (e: Exception) {
//                _uiState.update {
//                    it.copy(isLoading = false, errorMessage = e.message ?: "Ошибка IMZO входа")
//                }
//            }
//        }
//    }
}