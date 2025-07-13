package com.tm.gamopedia

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.tm.gamopedia.di.initKoin

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Gamopedia",
    ) {
        initKoin()
        App()
    }
}