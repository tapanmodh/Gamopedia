package com.tm.search.domain.di

import com.tm.search.domain.useCases.SearchGamesUseCase
import org.koin.dsl.module

fun getSearchDomainModule() = module {
    factory { SearchGamesUseCase(searchRepository = get()) }
}