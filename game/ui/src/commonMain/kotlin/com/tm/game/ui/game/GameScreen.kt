package com.tm.game.ui.game

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tm.common.ui.listitem.GameItem
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    onFavoriteClick: () -> Unit,
    onSearchClick: () -> Unit,
    onClick: (Int) -> Unit
) {

    val viewModel = koinViewModel<GameViewModel>()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    GameScreenContent(
        modifier = modifier.fillMaxSize(),
        uiState = uiState.value,
        onFavoriteClick = onFavoriteClick,
        onSearchClick = onSearchClick,
        onClick = onClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreenContent(
    modifier: Modifier = Modifier,
    uiState: GameScreen.UiState,
    onFavoriteClick: () -> Unit,
    onSearchClick: () -> Unit,
    onClick: (Int) -> Unit
) {

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Gamopedia") },
                actions = {
                    IconButton(onClick = onSearchClick) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    }

                    IconButton(onClick = onFavoriteClick) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = null
                        )
                    }
                },

                )
        }) {

        if (uiState.isLoading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        if (uiState.error.isNotBlank()) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(uiState.error)
            }
        }

        uiState.data?.let { data ->
            LazyColumn(modifier = modifier.padding(it).fillMaxSize()) {
                items(data) { item ->
                    GameItem(
                        modifier = modifier.fillMaxSize(),
                        isDeleteShow = false,
                        item = item,
                        onClick = { onClick(item.id) },
                        onDeleteClick = { }
                    )
                }
            }
        }
    }
}