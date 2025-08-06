package tj.ojsk.egov.di

import tj.ojsk.egov.platform.MultiplatformSettingsWrapper
import tj.ojsk.egov.platform.NotificationsManager
import org.koin.core.module.Module
import org.koin.dsl.module
import tj.ojsk.egov.platform.BrowserLauncher
import tj.ojsk.egov.platform.DatabaseDriverFactory

actual fun platformModule(): Module = module {
    single { MultiplatformSettingsWrapper().createSettings() }
    single { NotificationsManager() }
    single { DatabaseDriverFactory() }
    single { BrowserLauncher() }
}