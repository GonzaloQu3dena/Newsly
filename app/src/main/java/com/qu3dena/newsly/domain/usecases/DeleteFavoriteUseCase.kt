package com.qu3dena.newsly.domain.usecases

import com.qu3dena.newsly.domain.models.News
import com.qu3dena.newsly.domain.repositories.NewsRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case for removing a news article from the favorites list.
 *
 * @property newsRepository Repository providing access to favorite news management.
 */
class DeleteFavoriteUseCase(private val newsRepository: NewsRepository) {

    /**
     * Removes the specified news article from the favorites list.
     *
     * @param news The [News] object to be removed from favorites.
     */
    suspend operator fun invoke(news: News): Flow<Unit> {
        return newsRepository.deleteFavorite(news)
    }
}