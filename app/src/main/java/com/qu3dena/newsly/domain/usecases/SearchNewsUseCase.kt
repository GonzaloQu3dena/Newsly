package com.qu3dena.newsly.domain.usecases

import com.qu3dena.newsly.domain.models.News
import com.qu3dena.newsly.domain.repositories.NewsRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case for searching news articles by a keyword.
 *
 * @property newsRepository Repository providing access to news data.
 */
class SearchNewsUseCase(private val newsRepository: NewsRepository) {

    /**
     * Searches for news articles that match the provided keyword.
     *
     * @param keyword The search term used to filter news articles.
     * @return A list of [News] objects matching the search criteria.
     */
    suspend operator fun invoke(keyword: String): Flow<List<News>> =
        newsRepository.searchNews(keyword)
}