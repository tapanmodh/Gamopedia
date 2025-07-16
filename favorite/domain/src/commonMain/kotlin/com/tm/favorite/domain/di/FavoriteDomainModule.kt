package com.tm.favorite.domain.di

import com.tm.favorite.domain.useCases.DeleteUseCase
import com.tm.favorite.domain.useCases.GetAllLocalCachedGamesUseCase
import com.tm.favorite.domain.useCases.UpsertUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

fun getFavoriteDomainModule(): Module {
    return module {
        factory { DeleteUseCase(get()) }
        factory { GetAllLocalCachedGamesUseCase(get()) }
        factory { UpsertUseCase(get()) }
    }
}