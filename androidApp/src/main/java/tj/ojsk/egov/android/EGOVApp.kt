package tj.ojsk.egov.android

import android.app.Application
import com.russhwolf.settings.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level
import tj.ojsk.egov.android.di.androidModule
import tj.ojsk.egov.di.KoinInit


class EGOVApp : Application() {
    override fun onCreate() {
        super.onCreate()

        KoinInit().init {
            androidLogger(level = if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(androidContext = this@EGOVApp)
            modules(
                listOf(
                    androidModule,
                ),
            )
        }
    }
}