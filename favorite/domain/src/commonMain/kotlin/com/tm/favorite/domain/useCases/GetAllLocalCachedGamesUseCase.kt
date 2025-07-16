package com.tm.favorite.domain.useCases

import com.tm.favorite.domain.repository.FavoriteRepository

class GetAllLocalCachedGamesUseCase(private val favoriteRepository: FavoriteRepository) {

    operator fun invoke() = favoriteRepository.getAllGames()
}