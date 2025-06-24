package tj.ojsk.egov.feature.news

import androidx.lifecycle.ViewModel
import tj.ojsk.egov.core.domain.repository.settings.SettingsRepository

class NewsViewModel(
    private val settingsRepository: SettingsRepository,
) : ViewModel() {

}