package com.tm.game.domain.di

import com.tm.game.domain.useCases.GetGamesUseCase
import org.koin.dsl.module

fun getGameDomainModule() = module {
    factory { GetGamesUseCase(gameRepository = get()) }
}