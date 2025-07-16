package com.tm.favorite.domain.useCases

import com.tm.favorite.domain.repository.FavoriteRepository

class DeleteUseCase(private val favoriteRepository: FavoriteRepository) {

    suspend operator fun invoke(id: Int) = favoriteRepository.delete(id)
}