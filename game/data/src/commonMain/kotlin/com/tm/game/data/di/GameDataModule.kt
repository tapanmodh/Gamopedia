package com.tm.game.data.di

import com.tm.game.data.repository.GameRepositoryImpl
import com.tm.game.domain.repository.GameRepository
import org.koin.dsl.module

fun getGameDataModule() = module {
    factory <GameRepository> { GameRepositoryImpl(apiService = get()) }
}