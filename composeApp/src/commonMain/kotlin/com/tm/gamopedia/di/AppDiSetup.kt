package com.tm.gamopedia.di

import com.tm.coreDatabase.di.getCoreDatabaseModule
import com.tm.coreNetwork.di.getCoreNetworkModule
import com.tm.favorite.data.di.getFavoriteDataModule
import com.tm.favorite.domain.di.getFavoriteDomainModule
import com.tm.favorite.ui.di.getFavoriteUiModule
import com.tm.game.data.di.getGameDataModule
import com.tm.game.domain.di.getGameDomainModule
import com.tm.game.ui.di.getGameUiModule
import com.tm.seach.ui.di.getSearchUiModule
import com.tm.search.data.di.getSearchDataModule
import com.tm.search.domain.di.getSearchDomainModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

fun initKoin(koinApplication: ((KoinApplication) -> Unit)? = null) {
    startKoin {
        koinApplication?.invoke(this)
        modules(
            getCoreNetworkModule(),
            getGameDataModule(),
            getGameDomainModule(),
            getGameUiModule(),
            getSearchDataModule(),
            getSearchDomainModule(),
            getSearchUiModule(),
            getCoreDatabaseModule(),
            getFavoriteDataModule(),
            getFavoriteDomainModule(),
            getFavoriteUiModule()
        )
    }
}