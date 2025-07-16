package com.tm.coreDatabase.di

import app.cash.sqldelight.db.SqlDriver
import com.tm.coreDatabase.AppDatabase
import com.tm.coreDatabase.SqlDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun getCoreDatabaseModule(): Module {
    return module {
        single { SqlDriverFactory().getSqlDriver() }
        single {
            val driver = get<SqlDriver>()
            AppDatabase.invoke(driver)
        }
    }
}