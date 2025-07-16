package com.tm.coreDatabase

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class SqlDriverFactory actual constructor(context: Any?) {

    actual fun getSqlDriver(): SqlDriver {
        return NativeSqliteDriver(
            AppDatabase.Schema,
            name = "AppDatabase.db"
        )
    }
}