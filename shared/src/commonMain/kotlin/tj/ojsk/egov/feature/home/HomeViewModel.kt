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
import tj.ojsk.egov.core.data.remote.mapper.toDomain
import tj.ojsk.egov.core.data.remote.mapper.toDto
import tj.ojsk.egov.core.data.remote.model.auth.request.AuthDTO
import tj.ojsk.egov.core.data.remote.model.auth.response.UserResponseDTO
import tj.ojsk.egov.core.data.remote.model.documents.category.response.CategoriesResponseDTO
import tj.ojsk.egov.core.domain.model.auth.UserResponse
import tj.ojsk.egov.core.domain.model.reference.category.CategoriesResponse
import tj.ojsk.egov.core.domain.repository.settings.SettingsRepository
import tj.ojsk.egov.feature.auth.view_model.AuthResult.Error
import tj.ojsk.egov.feature.auth.view_model.AuthResult.Success
import tj.ojsk.egov.platform.Logger

class HomeViewModel(
    private val settingsRepository: SettingsRepository,
    private val networkClient: NetworkClient
) : ViewModel() {


    private val _state = MutableStateFlow<NetworkResult<UserResponse>?>(null)
    val state: StateFlow<NetworkResult<UserResponse>?> = _state

    fun testRequest() {
        viewModelScope.launch {
//            val result = networkClient.post<UserResponseDTO, AuthDTO>(
//                endpoint = AUTH_LOGIN,
//                body = AuthDTO("fiz_fo@gmail.com", "x5a9Iexz7XRX")
//            )

            val response = networkClient.get<CategoriesResponseDTO>(
                endpoint = DOCUMENTS_CATEGORIES,
            )

            when (response) {
                is NetworkResult.Success -> {
                    val userResponse = response.data
                    Logger.d("UserResponse", "UserResponse: $userResponse")
                    val userData = userResponse.data
                    Logger.d("UserData", "UserData: $userData")

                    Success
                }
                is NetworkResult.HttpError -> {
                    Error("Ошибка: ${response.code} ${response.message}")
                }
                is NetworkResult.NetworkError -> {
                    Error("Сетевая ошибка: ${response.exception.message}")
                }

                is NetworkResult.Unauthorized -> {
                    Error("Неверные учетные данные или истек срок действия токена")
                }
            }

        }
    }

}