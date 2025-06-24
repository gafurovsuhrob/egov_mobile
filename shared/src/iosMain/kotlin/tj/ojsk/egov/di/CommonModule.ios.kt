package tj.ojsk.egov.di

import tj.ojsk.egov.platform.MultiplatformSettingsWrapper
import tj.ojsk.egov.platform.NotificationsManager
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { MultiplatformSettingsWrapper().createSettings() }
    single { NotificationsManager() }
}