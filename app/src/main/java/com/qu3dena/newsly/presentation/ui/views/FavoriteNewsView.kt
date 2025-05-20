package com.qu3dena.newsly.presentation.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.qu3dena.newsly.presentation.viewmodels.FavoriteNewsViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.qu3dena.newsly.domain.models.News
import androidx.compose.foundation.lazy.items

/**
 * Displays the list of favorite news articles.
 *
 * @param viewModel The [FavoriteNewsViewModel] providing UI state and actions.
 */
@Composable
fun FavoriteNewsView(
    viewModel: FavoriteNewsViewModel,
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            uiState.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            uiState.errorMessage != null -> {
                Text(
                    text = "Error: ${uiState.errorMessage}",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            else -> {
                LazyColumn(modifier = Modifier.padding(16.dp)) {
                    items(uiState.favorites) { news ->
                        FavoriteNewsItem(
                            news = news,
                            onDelete = { viewModel.onDeleteFavorite(it) }
                        )
                    }
                }
            }
        }
    }
}

/**
 * Displays a single favorite news article item with a delete action.
 *
 * @param news The [News] article to display.
 * @param onDelete Callback when the delete icon is clicked.
 */
@Composable
private fun FavoriteNewsItem(
    news: News,
    onDelete: (News) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation()
    ) {
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            /**
             * Displays the news image.
             */
            AsyncImage(
                model = news.urlToImage,
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )

            Spacer(Modifier.width(8.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = news.title, fontWeight = FontWeight.Bold)
                Text(
                    text = "${news.author ?: "Unknown"} • ${news.publishedAt?.take(4) ?: "----"}"
                )
            }

            /**
             * Delete favorite button.
             */
            IconButton(onClick = { onDelete(news) }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Favorite",
                )
            }
        }
    }
}