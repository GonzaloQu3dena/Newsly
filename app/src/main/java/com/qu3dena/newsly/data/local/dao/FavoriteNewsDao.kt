package com.qu3dena.newsly.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.qu3dena.newsly.data.local.dto.FavoriteNewsDto
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for managing favorite news articles in the local database.
 *
 * Provides methods to insert, retrieve, and delete favorite news entries.
 */
@Dao
interface FavoriteNewsDao {

    /**
     * Inserts a [FavoriteNewsDto] entity into the database.
     * If a conflict occurs (e.g., duplicate title), the existing entry is replaced.
     *
     * @param entity The [FavoriteNewsDto] to insert or update.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: FavoriteNewsDto)

    /**
     * Retrieves all favorite news articles from the database.
     *
     * @return A list of [FavoriteNewsDto] representing favorite articles.
     */
    @Query("SELECT * FROM favorite_news")
    fun getAll(): Flow<List<FavoriteNewsDto>>

    /**
     * Deletes the specified [FavoriteNewsDto] entity from the database.
     *
     * @param entity The [FavoriteNewsDto] to remove from the database.
     */
    @Delete
    suspend fun delete(entity: FavoriteNewsDto)
}