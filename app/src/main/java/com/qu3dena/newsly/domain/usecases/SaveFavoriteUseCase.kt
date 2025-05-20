package com.qu3dena.newsly.domain.usecases

import com.qu3dena.newsly.domain.models.News
import com.qu3dena.newsly.domain.repositories.NewsRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case for saving a news article as a favorite.
 *
 * @property newsRepository Repository providing access to favorite news management.
 */
class SaveFavoriteUseCase(private val newsRepository: NewsRepository) {

    /**
     * Saves the specified news article as a favorite.
     *
     * @param news The [News] object to be marked as favorite.
     */
    suspend operator fun invoke(news: News): Flow<Unit> {
        return newsRepository.saveFavorite(news)
    }
}