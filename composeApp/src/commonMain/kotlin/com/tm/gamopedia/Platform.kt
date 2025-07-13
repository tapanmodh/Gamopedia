package com.tm.gamopedia

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform