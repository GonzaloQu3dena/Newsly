package com.qu3dena.newsly.presentation.ui.views

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.qu3dena.newsly.domain.models.News
import com.qu3dena.newsly.presentation.viewmodels.FindNewsViewModel
import androidx.compose.foundation.lazy.items
import androidx.core.net.toUri

/**
 * Displays the main view for finding news articles.
 *
 * @param viewModel The [FindNewsViewModel] providing UI state and actions.
 */
@Composable
fun FindNewsView(
    viewModel: FindNewsViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    var keyword by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        /**
         * Text field for entering the search keyword.
         */
        OutlinedTextField(
            value = keyword,
            onValueChange = { keyword = it },
            label = { Text("Search News") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        /**
         * Button to trigger the search action.
         */
        Button(
            onClick = { viewModel.onSearch(keyword) },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Search")
        }

        Spacer(Modifier.height(16.dp))

        /**
         * Displays loading, error, or the list of news articles based on UI state.
         */
        when {
            uiState.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            uiState.errorMessage != null -> {
                Text(
                    text = "Error: ${uiState.errorMessage}",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            else -> {
                LazyColumn {
                    items(uiState.newsList) { news ->
                        NewsItem(
                            news = news,
                            onToggleFavorite = { viewModel.onToggleFavorite(it) },
                            onOpenUrl = {
                                val intent = Intent(Intent.ACTION_VIEW, it.url?.toUri())
                                context.startActivity(intent)
                            }
                        )
                    }
                }
            }
        }
    }
}

/**
 * Displays a single news article item with favorite and open-in-browser actions.
 *
 * @param news The [News] article to display.
 * @param onOpenUrl Callback when the open-in-browser icon is clicked.
 * @param onToggleFavorite Callback when the favorite icon is toggled.
 */
@Composable
private fun NewsItem(
    news: News,
    onOpenUrl: (News) -> Unit,
    onToggleFavorite: (News) -> Unit,
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
             * Favorite toggle button.
             */
            IconButton(onClick = { onToggleFavorite(news) }) {
                Icon(
                    imageVector = if (news.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Toggle Favorite"
                )
            }

            /**
             * Open in browser button.
             */
            IconButton(onClick = { onOpenUrl(news) }) {
                Icon(
                    imageVector = Icons.Default.OpenInBrowser,
                    contentDescription = "Open in Browser"
                )
            }
        }
    }
}