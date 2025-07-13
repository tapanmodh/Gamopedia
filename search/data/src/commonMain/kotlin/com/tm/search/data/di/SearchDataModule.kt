package com.tm.search.data.di

import com.tm.search.data.repository.SearchRepositoryImpl
import com.tm.search.domain.repository.SearchRepository
import org.koin.dsl.module

fun getSearchDataModule() = module {
    factory<SearchRepository> { SearchRepositoryImpl(apiService = get()) }
}