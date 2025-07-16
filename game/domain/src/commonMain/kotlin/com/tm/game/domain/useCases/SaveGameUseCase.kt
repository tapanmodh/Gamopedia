package com.tm.game.domain.useCases

import com.tm.game.domain.repository.GameRepository

class SaveGameUseCase(private val gameRepository: GameRepository) {

    suspend operator fun invoke(id: Int, image: String, name: String) =
        gameRepository.save(id, image, name)
}