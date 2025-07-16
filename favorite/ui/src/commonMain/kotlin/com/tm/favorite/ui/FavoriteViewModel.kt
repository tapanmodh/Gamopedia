package com.tm.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tm.favorite.domain.useCases.DeleteUseCase
import com.tm.favorite.domain.useCases.GetAllLocalCachedGamesUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoriteViewModel(
    getAllLocalCachedGamesUseCase: GetAllLocalCachedGamesUseCase,
    private val deleteUseCase: DeleteUseCase
) : ViewModel() {

    val games = getAllLocalCachedGamesUseCase.invoke().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(), emptyList()
    )

    fun delete(id: Int) = viewModelScope.launch {
        deleteUseCase.invoke(id)
    }
}