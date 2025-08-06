package tj.ojsk.egov.platform

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import tj.ojsk.egov.database.EGOVDatabase


actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(schema = EGOVDatabase.Schema, name = "egov.db")
    }
}
