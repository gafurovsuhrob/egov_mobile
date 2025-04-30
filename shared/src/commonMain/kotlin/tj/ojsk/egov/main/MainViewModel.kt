package tj.ojsk.egov.main

import tj.ojsk.egov.core.domain.repository.settings.SettingsRepository


class MainViewModel(settingsRepository: SettingsRepository)  {
}

sealed class OnBoardingState {
    data object Loading : OnBoardingState()
    data class Success(val completed: Boolean) : OnBoardingState()
}