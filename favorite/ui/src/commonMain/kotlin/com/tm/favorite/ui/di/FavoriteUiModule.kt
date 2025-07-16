package com.tm.favorite.ui.di

import com.tm.favorite.ui.FavoriteViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

fun getFavoriteUiModule(): Module {
    return module {
        viewModel {
            FavoriteViewModel(
                getAllLocalCachedGamesUseCase = get(),
                deleteUseCase = get()
            )
        }
    }
}