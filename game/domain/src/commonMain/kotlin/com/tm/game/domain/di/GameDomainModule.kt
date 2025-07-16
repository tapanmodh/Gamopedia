package com.tm.game.domain.di

import com.tm.game.domain.useCases.DeleteUseCase
import com.tm.game.domain.useCases.GetGameDetailsUseCase
import com.tm.game.domain.useCases.GetGamesUseCase
import com.tm.game.domain.useCases.SaveGameUseCase
import org.koin.dsl.module

fun getGameDomainModule() = module {
    factory { GetGamesUseCase(gameRepository = get()) }
    factory { GetGameDetailsUseCase(gameRepository = get()) }
    factory { SaveGameUseCase(gameRepository = get()) }
    factory { DeleteUseCase(gameRepository = get()) }
}