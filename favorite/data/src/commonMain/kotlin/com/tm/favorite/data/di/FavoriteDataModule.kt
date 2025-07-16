package com.tm.favorite.data.di

import com.tm.coreDatabase.AppDatabase
import com.tm.favorite.data.repository.FavoriteRepoImpl
import com.tm.favorite.domain.repository.FavoriteRepository
import org.koin.core.module.Module
import org.koin.dsl.module

fun getFavoriteDataModule(): Module {
    return module {
        factory<FavoriteRepository> { FavoriteRepoImpl(get<AppDatabase>()) }
    }
}