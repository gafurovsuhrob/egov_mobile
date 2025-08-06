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
import tj.ojsk.egov.core.data.local.manager.UserManager
import tj.ojsk.egov.core.data.remote.NetworkClient
import tj.ojsk.egov.core.data.remote.NetworkResult
import tj.ojsk.egov.core.data.remote.model.auth.response.AuthRedirectResponse
import tj.ojsk.egov.core.domain.repository.auth.AuthRepository
import tj.ojsk.egov.platform.BrowserLauncher
import tj.ojsk.egov.platform.Logger
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class AuthViewModel(
    private val authRepository: AuthRepository,
    private val browserLauncher: BrowserLauncher,
    private val networkClient: NetworkClient,
    private val userManager: UserManager
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
                    LoginMethod.IMZO -> startImzoLogin()
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

    private fun updateState(block: LoginUiState.() -> LoginUiState) {
        _uiState.update(block)
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

    fun startImzoLogin() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            openImzoAuthorizationPage()

            _uiState.update { it.copy(isLoading = false) }
        }
    }

    fun loginWithAuthorizationCode(code: String) {
        viewModelScope.launch {
            when (val result = authRepository.loginWithImzoCode(code)) {
                is AuthResult.Success -> {
                    updateState { copy(isLoggedIn = true) }
                    login()
                }
                is AuthResult.Error -> updateState { copy(errorMessage = result.message) }
            }
        }
    }

    fun loginWithAuthorizationCode2(code: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            when (val result = authRepository.loginWithImzoCode(code)) {
                is AuthResult.Success -> {
                    when (val result = authRepository.loginWithImzoCode(code)) {
                        is AuthResult.Success -> {
                            updateState { copy(isLoading = false, isLoggedIn = true) }
                            login()
                        }
                        is AuthResult.Error -> {
                            updateState { copy(isLoading = false, errorMessage = result.message) }
                        }
                    }
                }

                is AuthResult.Error -> {
                    updateState { copy(isLoading = false, errorMessage = result.message) }
                }
            }
        }
    }

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

    private suspend fun openImzoAuthorizationPage() {
        val queryParams = mapOf(
            "response_type" to "code",
            "client_id" to "imzo",
            "redirect_uri" to "egov://oauth/callback",
            "scope" to "imzo",
            "state" to generateRandomState()
        )

        val result = networkClient.post<AuthRedirectResponse, Unit>(
            endpoint = "oauth/sso-url",
            queryParams = queryParams,
            authorized = false
        )

        when (result) {
            is NetworkResult.Success -> browserLauncher.openUrl(result.data.url)
            is NetworkResult.HttpError -> Logger.e("IMZO", "Ошибка: ${result.message}")
            is NetworkResult.NetworkError -> Logger.e("IMZO", "Сеть: ${result.exception.message}")
            is NetworkResult.Unauthorized -> Logger.e("IMZO", "Unauthorized")
        }
    }

    @OptIn(ExperimentalUuidApi::class)
    private fun generateRandomState(): String {
        return Uuid.random().toString()
    }
}