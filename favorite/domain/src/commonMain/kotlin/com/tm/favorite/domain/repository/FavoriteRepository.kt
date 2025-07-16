package com.tm.favorite.domain.repository

import com.tm.common.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    fun getAllGames(): Flow<List<Game>>

    suspend fun upsert(id: Int, name: String, image: String)

    suspend fun delete(id: Int)
}