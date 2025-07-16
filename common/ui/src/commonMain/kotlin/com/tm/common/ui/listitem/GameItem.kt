package com.tm.common.ui.listitem

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.tm.common.domain.model.Game

@Composable
fun GameItem(
    modifier: Modifier = Modifier,
    isDeleteShow: Boolean = false,
    item: Game,
    onClick: (Int) -> Unit, onDeleteClick: (Int) -> Unit
) {
    Card(
        modifier = modifier.padding(12.dp).fillMaxWidth().height(350.dp)
            .clickable { onClick(item.id) },
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(Modifier.fillMaxSize()) {
            AsyncImage(
                model = item.imageUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().height(350.dp),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(12.dp)
                    ).fillMaxWidth().align(
                        Alignment.BottomCenter
                    )
            ) {
                Text(
                    item.name,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            if (isDeleteShow) {
                IconButton(
                    onClick = { onDeleteClick(item.id) },
                    modifier = Modifier
                        .padding(12.dp)
                        .background(color = Color.White, shape = CircleShape)
                        .align(Alignment.TopEnd)
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