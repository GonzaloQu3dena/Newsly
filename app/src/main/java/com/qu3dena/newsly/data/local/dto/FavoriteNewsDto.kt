package com.qu3dena.newsly.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.qu3dena.newsly.domain.models.News

/**
 * Entity representing a news article marked as favorite in the local database.
 *
 * @property title The title of the news article, used as the primary key.
 * @property author The author of the news article.
 * @property imageUrl The URL of the article's image, or null if not available.
 * @property description A brief description of the news article.
 */
@Entity(tableName = "favorite_news")
data class FavoriteNewsDto(
    @PrimaryKey val title: String,
    val author: String,
    val imageUrl: String?,
    val description: String,
) {
    /**
     * Converts this [FavoriteNewsDto] to a [News] domain model.
     *
     * @return The corresponding [News] domain object with favorite flag set to true.
     */
    fun toDomain(): News {
        return News(
            author = author,
            title = title,
            description = description,
            url = null,
            urlToImage = imageUrl,
            publishedAt = null,
            content = null,
            sourceName = "",
            isFavorite = true
        )
    }

    companion object {
        /**
         * Creates a [FavoriteNewsDto] from a [News] domain model.
         *
         * @param news The [News] domain object to convert.
         * @return The corresponding [FavoriteNewsDto] object.
         */
        fun fromDomain(news: News): FavoriteNewsDto {
            return FavoriteNewsDto(
                title = news.title,
                author = news.author ?: "",
                imageUrl = news.urlToImage,
                description = news.description
            )
        }
    }
}