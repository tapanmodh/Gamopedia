package com.tm.game.ui.gameDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GameDetailsScreen(modifier: Modifier = Modifier, id: String, onBackClick: () -> Unit) {

    val viewModel = koinViewModel<GameDetailsViewModel>()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(id) {
        viewModel.getGameDetails(id.toInt())
    }
    GameDetailsScreenContent(
        modifier = modifier.fillMaxSize(), uiState = uiState.value,
        onSave = { id, name, image -> viewModel.save(id, image, name) },
        onDelete = { viewModel.delete(it) },
        onBackClick = onBackClick
    )
}

@Composable
fun GameDetailsScreenContent(
    modifier: Modifier = Modifier,
    uiState: GameDetailsScreen.UiState,
    onDelete: (Int) -> Unit,
    onSave: (id: Int, title: String, image: String) -> Unit,
    onBackClick: () -> Unit
) {

    if (uiState.isLoading) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }

    if (uiState.error.isNotBlank()) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(uiState.error)
        }
    }

    uiState.data?.let { data ->
        Box(modifier = modifier.fillMaxSize().systemBarsPadding()) {
            LazyColumn(modifier = Modifier.fillMaxSize())
            {
                item {
                    AsyncImage(
                        model = data.backgroundImage, contentDescription = null,
                        modifier = Modifier.fillMaxWidth().height(350.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                item {
                    Text(
                        modifier = Modifier.padding(12.dp).fillMaxWidth(),
                        text = data.name,
                        style = MaterialTheme.typography.headlineLarge
                    )
                }

                item {
                    Text(
                        text = data.description, style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                            .fillMaxWidth()
                    )
                }

                item {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Platforms",
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier.padding(horizontal = 12.dp).padding(top = 24.dp)
                        )
                        LazyRow(modifier = Modifier.fillMaxWidth()) {
                            items(data.platforms) {
                                Card(
                                    modifier = Modifier.padding(12.dp).wrapContentSize(),
                                    shape = RoundedCornerShape(12.dp),
                                    elevation = CardDefaults.cardElevation(6.dp)
                                ) {
                                    Column(
                                        modifier = Modifier.width(150.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        AsyncImage(
                                            model = it.image,
                                            contentDescription = null,
                                            modifier = Modifier
                                                .padding(8.dp)
                                                .background(
                                                    color = Color.Transparent,
                                                    shape = CircleShape
                                                ).clip(CircleShape)
                                                .size(120.dp),
                                            contentScale = ContentScale.Crop
                                        )
                                        Text(
                                            modifier = Modifier.padding(vertical = 8.dp),
                                            text = it.name,
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                    }
                                }
                            }
                        }
                    }
                }


                item {
                    Text(
                        text = "Stores",
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.padding(horizontal = 12.dp).padding(top = 24.dp)
                            .padding(bottom = 12.dp)
                    )
                }
                items(data.stores) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp).padding(bottom = 8.dp)
                            .fillMaxWidth()
                    ) {

                        AsyncImage(
                            model = it.image, contentDescription = null,
                            modifier = Modifier.size(120.dp)
                                .background(
                                    color = Color.Transparent,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(Modifier.width(8.dp))

                        Column(modifier = Modifier.weight(1f)) {

                            Text(
                                text = it.name, style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier.padding(end = 8.dp)
                            )

                            Spacer(Modifier.height(8.dp))

                            Text(
                                text = it.domain,
                                style = MaterialTheme.typography.bodyMedium,
                                textDecoration = TextDecoration.Underline
                            )

                            Spacer(Modifier.height(8.dp))
                            Text(
                                text = "Gamecount: " + it.gameCount,
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    }
                }

                item {
                    Text(
                        text = "Tags",
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.padding(horizontal = 12.dp).padding(top = 24.dp)
                    )
                }

                item {
                    FlowRow(modifier = Modifier.padding(horizontal = 12.dp).fillMaxWidth()) {
                        data.tags.forEach { tag ->
                            Row(
                                modifier = Modifier.padding(top = 8.dp, end = 12.dp).background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(200.dp)
                                ).clip(
                                    RoundedCornerShape(200.dp)
                                ),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AsyncImage(
                                    model = tag.image, contentDescription = null,
                                    modifier = Modifier.size(35.dp)
                                        .background(color = Color.Transparent, shape = CircleShape)
                                        .border(
                                            width = .5.dp,
                                            color = Color.LightGray,
                                            shape = RoundedCornerShape(200.dp)
                                        )
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Crop
                                )

                                Spacer(Modifier.width(4.dp))

                                Text(
                                    text = tag.name, style = MaterialTheme.typography.labelMedium,
                                    modifier = Modifier.padding(end = 8.dp)
                                )
                            }
                        }
                    }
                }

                item {
                    Text(
                        text = "Developers",
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.padding(horizontal = 12.dp)
                            .padding(top = 24.dp, bottom = 12.dp)
                    )
                }

                items(data.developers) {
                    Row(
                        modifier = Modifier.padding(12.dp).padding(bottom = 8.dp).fillMaxWidth(),
                        verticalAlignment = Alignment.Top
                    ) {

                        AsyncImage(
                            model = it.image, contentDescription = null,
                            modifier = Modifier.size(120.dp)
                                .background(
                                    color = Color.Transparent,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(Modifier.width(8.dp))

                        Column(modifier = Modifier.weight(1f)) {

                            Text(
                                text = it.name, style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier.padding(end = 8.dp)
                            )

                            Spacer(Modifier.height(8.dp))
                            Text(
                                text = "Gamecount: " + it.gameCount,
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    }
                }

            }
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
                    .fillMaxWidth()
            ) {

                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.background(color = Color.White, shape = CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier.padding(4.dp)
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = {
                        onSave(data.id, data.name, data.backgroundImage)
                    },
                    modifier = Modifier.background(color = Color.White, shape = CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite, contentDescription = null,
                        modifier = Modifier.padding(4.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))


                IconButton(
                    onClick = {
                        onDelete(data.id)
                    },
                    modifier = Modifier.background(color = Color.White, shape = CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete, contentDescription = null,
                        modifier = Modifier.padding(4.dp)
                    )
                }

            }
        }
    }
}