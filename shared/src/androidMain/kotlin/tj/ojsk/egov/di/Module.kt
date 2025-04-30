package tj.ojsk.egov.di

import org.koin.core.module.Module
import org.koin.dsl.module
import tj.ojsk.egov.platform.MultiplatformSettingsWrapper
import tj.ojsk.egov.platform.NotificationsManager

actual fun platformModule(): Module = module {
    single { MultiplatformSettingsWrapper(context = get()).createSettings() }
//    single { DatabaseDriverFactory(context = get()) }
    single { NotificationsManager(context = get()) }
}
