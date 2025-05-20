package com.qu3dena.newsly.domain.repositories

import com.qu3dena.newsly.domain.models.News
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for managing and accessing news articles and user favorites.
 *
 * Provides methods for searching news, retrieving details, and managing favorites.
 */
interface NewsRepository {

    /**
     * Searches for news articles that match the provided keyword.
     *
     * @param keyword Search term used to filter news articles.
     * @return A list of [News] objects matching the search criteria.
     */
    suspend fun searchNews(keyword: String): Flow<List<News>>

    /**
     * Saves the specified news article as a favorite.
     *
     * @param news The [News] object to be marked as favorite.
     */
    suspend fun saveFavorite(news: News): Flow<Unit>

    /**
     * Retrieves the list of news articles marked as favorites by the user.
     *
     * @return A list of [News] objects currently marked as favorites.
     */
    suspend fun getFavorites(): Flow<List<News>>

    /**
     * Removes the specified news article from the favorites list.
     *
     * @param news The [News] object to be removed from favorites.
     */
    suspend fun deleteFavorite(news: News): Flow<Unit>
}