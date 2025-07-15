package com.tm.game.domain.repository

import com.tm.common.domain.model.Game
import com.tm.game.domain.model.GameDetails

interface GameRepository {

    suspend fun getGames(): Result<List<Game>>

    suspend fun getDetails(id: Int): Result<GameDetails>
}