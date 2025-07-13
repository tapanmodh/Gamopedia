package com.tm.gamopedia

import android.app.Application
import com.tm.gamopedia.di.initKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}