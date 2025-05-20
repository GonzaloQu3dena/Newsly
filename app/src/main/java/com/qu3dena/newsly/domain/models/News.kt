package com.qu3dena.newsly.domain.models

/**
 * Domain model representing a news article.
 *
 * @property author Name of the author of the article, or `null` if not available.
 * @property title Title of the news article.
 * @property description Brief summary or description of the article.
 * @property url URL linking to the full article, or `null` if not available.
 * @property urlToImage URL linking to an image associated with the article, or `null` if not available.
 * @property publishedAt Publication date and time in ISO 8601 format.
 * @property content Full content of the news article.
 * @property sourceName Name of the news source or publisher.
 * @property isFavorite Boolean flag indicating if the article is marked as a favorite.
 */
data class News(
    val author: String?,
    val title: String,
    val description: String,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?,
    val sourceName: String,
    val isFavorite: Boolean = false
)