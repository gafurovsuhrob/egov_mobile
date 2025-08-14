package tj.ojsk.egov.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import tj.ojsk.egov.core.constants.AUTH_LOGIN
import tj.ojsk.egov.core.constants.DOCUMENTS_CATEGORIES
import tj.ojsk.egov.core.data.remote.NetworkClient
import tj.ojsk.egov.core.data.remote.NetworkResult
import tj.ojsk.egov.core.data.remote.model.auth.request.AuthDTO
import tj.ojsk.egov.core.domain.model.auth.UserResponse
import tj.ojsk.egov.core.domain.repository.settings.SettingsRepository

class HomeViewModel(
    private val settingsRepository: SettingsRepository,
    private val networkClient: NetworkClient
) : ViewModel() {


    private val _state = MutableStateFlow<NetworkResult<UserResponse>?>(null)
    val state: StateFlow<NetworkResult<UserResponse>?> = _state

    fun testRequest() {
        viewModelScope.launch {
            val result = networkClient.post<UserResponse, AuthDTO>(
                endpoint = AUTH_LOGIN,
                body = AuthDTO("fiz_fo@gmail.com", "x5a9Iexz7XRX")
            )
            _state.value = result
        }
    }


    private val _categories = MutableStateFlow<NetworkResult<CategoriesResponse>?>(null)
    val categories: StateFlow<NetworkResult<CategoriesResponse>?> = _categories

    fun getCategories() {
        viewModelScope.launch {
            val result = networkClient.get<CategoriesResponse>(DOCUMENTS_CATEGORIES)
            _categories.value = result
        }
    }
}