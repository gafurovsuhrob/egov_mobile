package tj.ojsk.egov.feature.services

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import tj.ojsk.egov.core.data.remote.NetworkResult
import tj.ojsk.egov.core.domain.model.reference.category.Category
import tj.ojsk.egov.core.domain.repository.reference.categories.CategoryRepository
import tj.ojsk.egov.core.presentation.state.LoadingDialogState

class ServicesViewModel(
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    private val _categoryList = MutableStateFlow<List<Category>>(emptyList())
    val categoryList: StateFlow<List<Category>> = _categoryList

    private val _loadingDialogState = MutableStateFlow<LoadingDialogState>(LoadingDialogState.None)
    val loadingDialogState: StateFlow<LoadingDialogState> = _loadingDialogState

    fun loadCategories() {
        viewModelScope.launch {
            _loadingDialogState.value = LoadingDialogState.Loading

            when (val result = categoryRepository.getCategories()) {
                is NetworkResult.Success -> {
                    _categoryList.value = result.data
                    _loadingDialogState.value = LoadingDialogState.None
                }
                is NetworkResult.HttpError -> {
                    _loadingDialogState.value = LoadingDialogState.Error("HTTP ошибка: ${result.code}")
                }
                is NetworkResult.Unauthorized -> {
                    _loadingDialogState.value = LoadingDialogState.Error("Не авторизовано")
                }
                is NetworkResult.NetworkError -> {
                    _loadingDialogState.value = LoadingDialogState.Error("Сетевая ошибка: ${result.exception.message ?: "Неизвестная ошибка"}")
                }
            }
        }
    }

    fun dismissDialog() {
        _loadingDialogState.value = LoadingDialogState.None
    }
}