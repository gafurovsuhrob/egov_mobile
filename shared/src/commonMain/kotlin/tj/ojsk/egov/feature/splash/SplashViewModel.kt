package tj.ojsk.egov.feature.splash

import androidx.lifecycle.ViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import tj.ojsk.egov.core.domain.repository.auth.AuthRepository
import tj.ojsk.egov.core.domain.repository.preload.PreloadRepository
import tj.ojsk.egov.core.domain.repository.settings.SettingsRepository
import kotlin.getValue

class SplashViewModel(
    private val authRepository: AuthRepository,
    private val preloadRepository: PreloadRepository
) : ViewModel() {

    private val _state = MutableStateFlow<SplashState>(SplashState.Loading)
    val state: StateFlow<SplashState> = _state

    private val scope = CoroutineScope(Dispatchers.Default)

    init {
        load()
    }

    private fun load() {
        scope.launch {
//            val isLoggedIn = authRepository.isLoggedIn()
            val isLoggedIn = false
            preloadRepository.loadInitialData()
            _state.value = SplashState.Success(isLoggedIn)
        }
    }
}

sealed class SplashState {
    object Loading : SplashState()
    data class Success(val isLoggedIn: Boolean) : SplashState()
}