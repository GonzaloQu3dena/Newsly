package com.qu3dena.newsly.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.qu3dena.newsly.data.local.dao.FavoriteNewsDao
import com.qu3dena.newsly.data.local.dto.FavoriteNewsDto

@Database(entities = [FavoriteNewsDto::class], version = 1, exportSchema = false)
/**
 * Abstract Room database for managing local storage of favorite news articles.
 *
 * Extends [RoomDatabase] and provides access to DAOs for database operations.
 */
abstract class NewsDatabase : RoomDatabase() {

    /**
     * Provides access to favorite news DAO for database operations.
     *
     * @return An instance of [FavoriteNewsDao].
     */
    abstract fun favoriteNewsDao(): FavoriteNewsDao

    companion object {
        /** Name of the database file. */
        const val DATABASE_NAME = "news_database"
    }
}