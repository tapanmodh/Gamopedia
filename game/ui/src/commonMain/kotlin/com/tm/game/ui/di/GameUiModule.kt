package com.tm.game.ui.di

import com.tm.game.ui.game.GameViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

fun getGameUiModule() = module {
    viewModel { GameViewModel(getGameUseCase = get()) }
}