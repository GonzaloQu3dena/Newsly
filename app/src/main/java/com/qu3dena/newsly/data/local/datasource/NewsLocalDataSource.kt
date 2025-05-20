package com.qu3dena.newsly.data.local.datasource

import com.qu3dena.newsly.data.local.dao.FavoriteNewsDao
import com.qu3dena.newsly.data.local.dto.FavoriteNewsDto
import kotlinx.coroutines.flow.Flow

/**
 * Local data source for managing favorite news articles using Room.
 *
 * Provides methods to insert, delete, and retrieve favorite news from the local database.
 *
 * @property favoriteNewsDao The DAO for favorite news operations.
 */
class NewsLocalDataSource(private val favoriteNewsDao: FavoriteNewsDao) {

    /**
     * Inserts a favorite news article into the local database.
     *
     * @param favoriteNews The [FavoriteNewsDto] to insert.
     */
    suspend fun insert(favoriteNews: FavoriteNewsDto) {
        favoriteNewsDao.insert(favoriteNews)
    }

    /**
     * Deletes a favorite news article from the local database.
     *
     * @param favoriteNews The [FavoriteNewsDto] to delete.
     */
    suspend fun delete(favoriteNews: FavoriteNewsDto) {
        favoriteNewsDao.delete(favoriteNews)
    }

    /**
     * Retrieves all favorite news articles as a [Flow] of list.
     *
     * @return A [Flow] emitting lists of [FavoriteNewsDto].
     */
    fun getAll(): Flow<List<FavoriteNewsDto>> {
        return favoriteNewsDao.getAll()
    }
}