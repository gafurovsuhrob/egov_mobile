package tj.ojsk.egov.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import tj.ojsk.egov.core.domain.repository.auth.AuthRepository
import tj.ojsk.egov.core.domain.repository.settings.SettingsRepository
import tj.ojsk.egov.core.presentation.state.LoadingDialogState


class MainViewModel(
    settingsRepository: SettingsRepository,
    authRepository: AuthRepository
) : ViewModel() {

    private val _loadingDialogState = MutableStateFlow<LoadingDialogState>(LoadingDialogState.None)
    val loadingDialogState: StateFlow<LoadingDialogState> = _loadingDialogState

    private val _imzoAuthCode = MutableStateFlow<String?>(null)
    val imzoAuthCode: StateFlow<String?> = _imzoAuthCode

    fun handleImzoCode(code: String) {
        _imzoAuthCode.value = code
    }

    init {
        println("MainViewModel created: ${this.hashCode()}")
    }

    val appTheme: StateFlow<Int?> = settingsRepository.getAppTheme().map { it }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = null,
    )

    val onBoardingCompleted: StateFlow<OnBoardingState> =
        settingsRepository.getUsername().map {
            OnBoardingState.Success(it.isNullOrEmpty().not())
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = OnBoardingState.Loading,
        )

    fun showLoading() {
        _loadingDialogState.value = LoadingDialogState.Loading
    }

    fun showSuccess(message: String? = null) {
        _loadingDialogState.value = LoadingDialogState.Success(message)
    }

    fun showError(message: String) {
        _loadingDialogState.value = LoadingDialogState.Error(message)
    }

    fun dismissDialog() {
        _loadingDialogState.value = LoadingDialogState.None
    }
}

sealed class OnBoardingState {
    data object Loading : OnBoardingState()
    data class Success(val completed: Boolean) : OnBoardingState()
}