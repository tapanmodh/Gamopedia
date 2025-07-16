package com.tm.coreDatabase

import app.cash.sqldelight.db.SqlDriver

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class SqlDriverFactory(context: Any? = null) {

    fun getSqlDriver(): SqlDriver
}