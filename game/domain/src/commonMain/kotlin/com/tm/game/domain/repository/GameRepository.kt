package com.tm.game.domain.repository

import com.tm.common.domain.model.Game

interface GameRepository {

    suspend fun getGames(): Result<List<Game>>
}