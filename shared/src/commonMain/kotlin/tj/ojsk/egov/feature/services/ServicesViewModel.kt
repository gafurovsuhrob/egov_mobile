package tj.ojsk.egov.feature.services

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import tj.ojsk.egov.core.constants.DOCUMENTS_CATEGORIES
import tj.ojsk.egov.core.data.remote.NetworkClient
import tj.ojsk.egov.core.data.remote.NetworkResult
import tj.ojsk.egov.core.domain.repository.settings.SettingsRepository

class ServicesViewModel(
    private val settingsRepository: SettingsRepository,
    private val networkClient: NetworkClient
) : ViewModel() {

//    private val _categories = MutableStateFlow<NetworkResult<CategoriesResponse>?>(null)
//    val categories: StateFlow<NetworkResult<CategoriesResponse>?> = _categories
//
//    fun getCategories() {
//        viewModelScope.launch {
//            val result = networkClient.get<CategoriesResponse>(DOCUMENTS_CATEGORIES)
//            _categories.value = result
//        }
//    }

}