package tj.ojsk.egov.di

import org.koin.core.module.Module
import org.koin.dsl.module
import tj.ojsk.egov.core.data.local.setting.PreferenceManager
import tj.ojsk.egov.core.data.repository.auth.AuthRepositoryImpl
import tj.ojsk.egov.core.data.repository.preload.PreloadRepositoryImpl
import tj.ojsk.egov.core.data.repository.settings.SettingsRepositoryImpl
import tj.ojsk.egov.core.domain.repository.auth.AuthRepository
import tj.ojsk.egov.core.domain.repository.preload.PreloadRepository
import tj.ojsk.egov.core.domain.repository.settings.SettingsRepository

fun commonModule() = module {
    /**
     * Database
     */



    /**
     * Multiplatform-Settings
     */
    single<PreferenceManager> {
        PreferenceManager(settings = get())
    }

    /**
     * Repositories
     */
    single<SettingsRepository> {
        SettingsRepositoryImpl(
            preferenceManager = get()
        )
    }
    single<AuthRepository> {
        AuthRepositoryImpl(
            preferenceManager = get()
        )
    }
    single<PreloadRepository> {
        PreloadRepositoryImpl()
    }

}

expect fun platformModule(): Module
