package com.qu3dena.newsly.domain.usecases

import com.qu3dena.newsly.domain.models.News
import com.qu3dena.newsly.domain.repositories.NewsRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case for retrieving the list of favorite news articles.
 *
 * @property newsRepository Repository providing access to favorite news articles.
 */
class GetFavoritesUseCase(private val newsRepository: NewsRepository) {

    /**
     * Retrieves the list of news articles marked as favorites by the user.
     *
     * @return A list of [News] objects currently marked as favorites.
     */
    suspend operator fun invoke(): Flow<List<News>> =
        newsRepository.getFavorites()
}