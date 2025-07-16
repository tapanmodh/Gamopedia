package com.tm.game.domain.useCases

import com.tm.game.domain.repository.GameRepository

class DeleteUseCase(private val gameRepository: GameRepository) {

    suspend operator fun invoke(id: Int) = gameRepository.delete(id)
}