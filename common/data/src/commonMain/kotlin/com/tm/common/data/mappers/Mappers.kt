package com.tm.common.data.mappers

import com.tm.common.domain.model.Game
import com.tm.coreNetwork.model.game.Result

fun List<Result>.toDomainListOfGames(): List<Game> = map {
    Game(
        id = it.id,
        name = it.name,
        imageUrl = it.background_image
    )
}