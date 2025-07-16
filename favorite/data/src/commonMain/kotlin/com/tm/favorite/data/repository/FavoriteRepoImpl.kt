package com.tm.favorite.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.tm.common.domain.model.Game
import com.tm.coreDatabase.AppDatabase
import com.tm.favorite.domain.repository.FavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteRepoImpl(
    private val appDatabase: AppDatabase
) : FavoriteRepository {
    override fun getAllGames(): Flow<List<Game>> {
        return appDatabase.appDatabaseQueries
            .getAllGames()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map {
                it.map {
                    Game(
                        id = it.id.toInt(),
                        name = it.name,
                        imageUrl = it.image
                    )
                }
            }
    }

    override suspend fun upsert(id: Int, name: String, image: String) {
        appDatabase.appDatabaseQueries
            .upsert(id.toLong(), image, name)
    }

    override suspend fun delete(id: Int) {
        appDatabase.appDatabaseQueries
            .delete(id.toLong())
    }
}