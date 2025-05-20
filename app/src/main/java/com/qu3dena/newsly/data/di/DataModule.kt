package com.qu3dena.newsly.data.di

import androidx.room.Room
import com.qu3dena.newsly.NewslyApplication
import com.qu3dena.newsly.data.local.dao.FavoriteNewsDao
import com.qu3dena.newsly.data.local.database.NewsDatabase
import com.qu3dena.newsly.data.local.datasource.NewsLocalDataSource
import com.qu3dena.newsly.data.remote.services.NewsApiService
import com.qu3dena.newsly.data.repositories.NewsRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Provides dependency injection for data-related components such as Retrofit, Room database,
 * DAOs, data sources, and repositories.
 */
object DataModule {

    /**
     * Creates and provides a [Retrofit] instance configured with the base URL and Gson converter.
     *
     * @return A configured [Retrofit] instance.
     */
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dev.formandocodigo.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Provides an implementation of [NewsApiService] using Retrofit.
     *
     * @return An instance of [NewsApiService].
     */
    fun getNewsApiService(): NewsApiService {
        return getRetrofit().create(NewsApiService::class.java)
    }

    /**
     * Creates and provides the Room [NewsDatabase] instance.
     *
     * @return The [NewsDatabase] instance.
     */
    fun getNewsDatabase(): NewsDatabase {
        return Room.databaseBuilder(
            NewslyApplication.instance.applicationContext,
            NewsDatabase::class.java, NewsDatabase.DATABASE_NAME
        ).build()
    }

    /**
     * Provides the [FavoriteNewsDao] for accessing favorite news in the database.
     *
     * @return The [FavoriteNewsDao] instance.
     */
    fun getFavoriteNewsDao(): FavoriteNewsDao {
        return getNewsDatabase().favoriteNewsDao()
    }

    /**
     * Provides the [NewsLocalDataSource] for local data operations.
     *
     * @return The [NewsLocalDataSource] instance.
     */
    fun getNewsLocalDataSource(): NewsLocalDataSource {
        return NewsLocalDataSource(getFavoriteNewsDao())
    }

    /**
     * Provides the [NewsRepositoryImpl] for managing news data from remote and local sources.
     *
     * @return The [NewsRepositoryImpl] instance.
     */
    fun getNewsRepository(): NewsRepositoryImpl {
        return NewsRepositoryImpl(
            getNewsApiService(),
            getNewsLocalDataSource()
        )
    }
}