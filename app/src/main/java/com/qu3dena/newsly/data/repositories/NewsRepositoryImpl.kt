package com.qu3dena.newsly.data.repositories

import android.util.Log
import com.qu3dena.newsly.data.local.datasource.NewsLocalDataSource
import com.qu3dena.newsly.data.local.dto.FavoriteNewsDto
import com.qu3dena.newsly.data.remote.services.NewsApiService
import com.qu3dena.newsly.domain.models.News
import com.qu3dena.newsly.domain.repositories.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.first

class NewsRepositoryImpl(
    private val newsApiService: NewsApiService,
    private val newsLocalDataSource: NewsLocalDataSource
) : NewsRepository {

    override suspend fun searchNews(keyword: String): Flow<List<News>> = flow {
        val remote = newsApiService.findNews(keyword).map { it.toDomain() }

        val favList = newsLocalDataSource.getAll().first()
        val favIds = favList.map { it.title }.toSet()

        emit(remote.map { news ->
            news.copy(isFavorite = favIds.contains(news.title))
        })
    }.flowOn(Dispatchers.IO)

    override suspend fun saveFavorite(news: News): Flow<Unit> = flow {
        newsLocalDataSource.insert(FavoriteNewsDto.fromDomain(news))
        emit(Unit)
    }.flowOn(Dispatchers.IO)

    override suspend fun deleteFavorite(news: News): Flow<Unit> = flow {
        newsLocalDataSource.delete(FavoriteNewsDto.fromDomain(news))
        emit(Unit)
    }.flowOn(Dispatchers.IO)

    override suspend fun getFavorites(): Flow<List<News>> {
        Log.d("NewsRepositoryImpl", "getFavorites called")
        return newsLocalDataSource.getAll().map { list ->
            list.map { it.toDomain() }
        }
    }
}