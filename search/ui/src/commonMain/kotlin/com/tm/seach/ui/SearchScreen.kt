package com.tm.seach.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SearchScreen(modifier: Modifier = Modifier, onClick: (Int) -> Unit, onBackClick: () -> Unit) {

    val viewModel = koinViewModel<SearchViewModel>()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val query = rememberSaveable { mutableStateOf("") }

    SearchScreenContent(
        modifier = modifier.fillMaxSize(), uiState.value, query.value,
        onQueryChange = {
            query.value = it
            viewModel.updateQuery(it)
        },
        onClick = onClick,
        onBackClick = onBackClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreenContent(
    modifier: Modifier = Modifier,
    uiState: SearchScreen.UiState,
    query: String,
    onQueryChange: (String) -> Unit,
    onClick: (Int) -> Unit,
    onBackClick: () -> Unit
) {

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Row(modifier = Modifier.padding(4.dp).fillMaxWidth()) {
                        IconButton(onClick = { onBackClick() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null
                            )
                        }
                        Spacer(Modifier.width(8.dp))
                        TextField(
                            value = query, onValueChange = onQueryChange,
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.colors(
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent
                            ), placeholder = {
                                Text("Search here...")
                            }
                        )
                    }
                })
        }) {

        if (uiState.isLoading) {
            Box(
                modifier = Modifier.padding(it).fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        if (uiState.error.isNotBlank()) {
            Box(
                modifier = Modifier.padding(it).fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(uiState.error)
            }
        }

        uiState.data?.let { data ->
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(it).fillMaxSize()
            ) {
                items(data) { item ->

                    AsyncImage(
                        item.imageUrl,
                        contentDescription = null,
                        modifier = Modifier.padding(12.dp).background(
                            color = Color.Transparent,
                            shape = RoundedCornerShape(12.dp)
                        ).clip(RoundedCornerShape(12.dp))
                            .height(250.dp)
                            .clickable { onClick(item.id) },
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}