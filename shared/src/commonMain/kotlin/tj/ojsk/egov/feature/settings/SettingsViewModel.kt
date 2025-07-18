package tj.ojsk.egov.feature.settings

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import tj.ojsk.egov.core.domain.repository.settings.SettingsRepository

class SettingsViewModel(
    private val settingsRepository: SettingsRepository,
) : ViewModel() {
    private val _selectedColorCardTitle = MutableStateFlow("")
    val selectedColorCardTitle = _selectedColorCardTitle.asStateFlow()
    fun setSelectedColorCardTitle(title: String) {
        _selectedColorCardTitle.value = title
    }

    val hourFormats: List<String> = listOf("12-hour", "24-hour")

    val optionsOpened = mutableStateListOf("")
    fun openOptions(option: String) {
        if (optionsOpened.contains(option)) {
            optionsOpened.remove(option)
        } else {
            optionsOpened.add(option)
        }
    }

    val appTheme: StateFlow<Int?> = settingsRepository.getAppTheme()
        .map { it }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = null,
        )

    fun setAppTheme(appTheme: Int) {
        viewModelScope.launch {
            settingsRepository.saveAppTheme(appTheme)
        }
    }

    val sessionTime = settingsRepository.getSessionTime()
        .map {
            it
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null,
        )

    fun setSessionTime(sessionTime: Int) {
        viewModelScope.launch {
            settingsRepository.saveSessionTime(sessionTime)
        }
    }

    val shortBreakTime = settingsRepository.getShortBreakTime()
        .map { it }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null,
        )

    fun setShortBreakTime(shortBreakTime: Int) {
        viewModelScope.launch {
            settingsRepository.saveShortBreakTime(shortBreakTime)
        }
    }

    val longBreakTime = settingsRepository.getLongBreakTime()
        .map { it }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null,
        )

    fun setLongBreakTime(longBreakTime: Int) {
        viewModelScope.launch {
            settingsRepository.saveLongBreakTime(longBreakTime)
        }
    }

    val timeFormat = settingsRepository.getHourFormat()
        .map { it }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null,
        )

    fun setHourFormat(timeFormat: Int) {
        viewModelScope.launch {
            settingsRepository.saveHourFormat(timeFormat)
        }
    }

    fun setShortBreakColor(color: Long) {
        viewModelScope.launch {
            settingsRepository.saveShortBreakColor(color)
        }
    }

    fun setLongBreakColor(color: Long) {
        viewModelScope.launch {
            settingsRepository.saveLongBreakColor(color)
        }
    }

    fun setFocusColor(color: Long) {
        viewModelScope.launch {
            settingsRepository.saveFocusColor(color)
        }
    }

    private val _showColorDialog = MutableStateFlow(false)
    val showColorDialog = _showColorDialog.asStateFlow()
    fun setShowColorDialog(it: Boolean) {
        _showColorDialog.value = it
    }

    val shortBreakColor = settingsRepository.shortBreakColor()
        .map {
            it
        }
        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = null,
        )

    val longBreakColor = settingsRepository.longBreakColor()
        .map { it }
        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = null,
        )

    val focusColor = settingsRepository.focusColor()
        .map { it }
        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = null,
        )

    val remindersOn = settingsRepository.remindersOn()
        .map { it }
        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = false,
        )

    fun setReminders(value: Int) {
        viewModelScope.launch {
            settingsRepository.toggleReminder(value)
        }
    }
}
