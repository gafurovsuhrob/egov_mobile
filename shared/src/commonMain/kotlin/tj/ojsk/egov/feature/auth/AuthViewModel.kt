package tj.ojsk.egov.feature.auth

import androidx.lifecycle.ViewModel
import tj.ojsk.egov.core.domain.repository.settings.SettingsRepository

class AuthViewModel(
    private val settingsRepository: SettingsRepository,
) : ViewModel() {

}