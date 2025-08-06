package tj.ojsk.egov.platform

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import tj.ojsk.egov.database.EGOVDatabase

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(EGOVDatabase.Schema, context, "egov.db")
    }
}