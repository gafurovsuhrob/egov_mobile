package tj.ojsk.egov.feature.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import tj.ojsk.egov.core.data.local.manager.UserManager
import tj.ojsk.egov.core.data.remote.mapper.toDomain
import tj.ojsk.egov.core.domain.model.auth.User
import tj.ojsk.egov.core.domain.repository.auth.AuthRepository
import tj.ojsk.egov.core.domain.repository.settings.SettingsRepository

class ProfileViewModel(
    private val authRepository: AuthRepository,
    private val userManager: UserManager
) : ViewModel() {

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn = flow {
        emitAll(authRepository.observeLoginState())
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    private val scope = CoroutineScope(Dispatchers.Default)

    init {
        checkLogin()
        viewModelScope.launch {
            _currentUser.value = userManager.getUser()?.toDomain()
        }
    }

    private fun checkLogin() {
        scope.launch {
            val result = authRepository.isLoggedIn()
            _isLoggedIn.update { result }
        }
    }
}