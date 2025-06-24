package tj.ojsk.egov.feature.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import tj.ojsk.egov.core.domain.repository.settings.SettingsRepository

class HomeViewModel(
    private val settingsRepository: SettingsRepository,
) : ViewModel() {

    private val _taskDescription = mutableStateOf("")
    val taskDescription: State<String> = _taskDescription
    fun setTaskDescription(description: String) {
        _taskDescription.value = description
    }

}