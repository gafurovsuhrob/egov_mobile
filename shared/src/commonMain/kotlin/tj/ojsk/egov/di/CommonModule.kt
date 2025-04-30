package tj.ojsk.egov.di

import org.koin.core.module.Module
import org.koin.dsl.module

fun commonModule() = module {
    /**
     * Database
     */
//    single<BloomDatabase> {
//        BloomDatabase(
//            driver = get<DatabaseDriverFactory>().createDriver(),
//            taskEntityAdapter = TaskEntity.Adapter(
//                idAdapter = idAdapter,
//                colorAdapter = colorAdapter,
//                consumedFocusTimeAdapter = consumedFocusTimeAdapter,
//                consumedLongBreakTimeAdapter = consumedLongBreakTimeAdapter,
//                consumedShortBreakTimeAdapter = consumedShortBreakTimeAdapter,
//                currentAdapter = currentAdapter,
//                currentCycleAdapter = currentCycleAdapter,
//                focusSessionsAdapter = focusSessionsAdapter,
//            ),
//        )
//    }
//    /**
//     * Multiplatform-Settings
//     */
//    single<PreferenceManager> {
//        PreferenceManager(settings = get())
//    }
//
//    /**
//     * Repositories
//     */
//    single<SettingsRepository> {
//        SettingsRepositoryImpl(
//            preferenceManager = get(),
//        )
//    }
//
//    single<TasksRepository> {
//        TasksRepositoryImpl(
//            bloomDatabase = get(),
//        )
//    }
}

expect fun platformModule(): Module
