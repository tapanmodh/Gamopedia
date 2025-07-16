package com.tm.coreDatabase

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class SqlDriverFactory actual constructor(context: Any?) {

    actual fun getSqlDriver(): SqlDriver {
        return JdbcSqliteDriver(
            "jdbc:sqlite:AppDatabase.db"
        )
    }
}