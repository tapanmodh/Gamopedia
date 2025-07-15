package com.tm.seach.ui.di

import com.tm.seach.ui.SearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

fun getSearchUiModule() = module {

    viewModel { SearchViewModel(searchGamesUseCase = get()) }
}