package tj.ojsk.egov.di

import org.koin.core.module.Module
import org.koin.dsl.module
import tj.ojsk.egov.core.data.local.dao.TokenDao
import tj.ojsk.egov.core.data.local.dao.TokenDaoImpl
import tj.ojsk.egov.core.data.local.dao.UserDao
import tj.ojsk.egov.core.data.local.dao.UserDaoImpl
import tj.ojsk.egov.core.data.local.manager.TokenManager
import tj.ojsk.egov.core.data.local.manager.UserManager
import tj.ojsk.egov.core.data.local.setting.PreferenceManager
import tj.ojsk.egov.core.data.repository.auth.AuthRepositoryImpl
import tj.ojsk.egov.core.data.repository.auth.TokenProviderImpl
import tj.ojsk.egov.core.data.repository.categories.CategoryRepositoryImpl
import tj.ojsk.egov.core.data.repository.preload.PreloadRepositoryImpl
import tj.ojsk.egov.core.data.repository.settings.SettingsRepositoryImpl
import tj.ojsk.egov.core.domain.repository.auth.AuthRepository
import tj.ojsk.egov.core.domain.repository.auth.TokenProvider
import tj.ojsk.egov.core.domain.repository.preload.PreloadRepository
import tj.ojsk.egov.core.domain.repository.reference.categories.CategoryRepository
import tj.ojsk.egov.core.domain.repository.settings.SettingsRepository
import tj.ojsk.egov.database.EGOVDatabase
import tj.ojsk.egov.platform.DatabaseDriverFactory

fun commonModule() = module {
    /**
     * Database
     */
    single {
        EGOVDatabase(
            driver = get<DatabaseDriverFactory>().createDriver()
        )
    }
    single<TokenDao> {
        TokenDaoImpl(get())
    }

    single<TokenManager> {
        TokenManager(get())
    }

    single<UserDao> {
        UserDaoImpl(get())
    }

    single<UserManager> {
        UserManager(get())
    }

    single<TokenProvider> {
        TokenProviderImpl(get())
    }
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
            preferenceManager = get(),
            networkClient = get(),
            tokenManager = get(),
            userManager = get()
        )
    }
    single<CategoryRepository>{
        CategoryRepositoryImpl(
            networkClient = get()
        )
    }
    single<PreloadRepository> {
        PreloadRepositoryImpl()
    }
}

expect fun platformModule(): Module
