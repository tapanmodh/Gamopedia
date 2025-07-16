package com.tm.favorite.domain.useCases

import com.tm.favorite.domain.repository.FavoriteRepository

class UpsertUseCase(private val favoriteRepository: FavoriteRepository) {

    suspend operator fun invoke(id: Int, image: String, name: String) =
        favoriteRepository.upsert(id, image = image, name = name)
}