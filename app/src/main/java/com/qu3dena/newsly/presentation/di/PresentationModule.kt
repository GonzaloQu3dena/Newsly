package com.qu3dena.newsly.presentation.di

import com.qu3dena.newsly.data.di.DataModule
import com.qu3dena.newsly.domain.usecases.DeleteFavoriteUseCase
import com.qu3dena.newsly.domain.usecases.GetFavoritesUseCase
import com.qu3dena.newsly.domain.usecases.SaveFavoriteUseCase
import com.qu3dena.newsly.domain.usecases.SearchNewsUseCase
import com.qu3dena.newsly.presentation.viewmodels.FavoriteNewsViewModel
import com.qu3dena.newsly.presentation.viewmodels.FindNewsViewModel

/**
 * Provides factory methods for creating ViewModel instances used in the presentation layer.
 */
object PresentationModule {

    /**
     * Creates an instance of [FavoriteNewsViewModel] with its required dependencies.
     *
     * @return A new [FavoriteNewsViewModel] instance.
     */
    fun getFavoriteNewsViewModel(): FavoriteNewsViewModel {
        val taskRepository = DataModule.getNewsRepository()

        return FavoriteNewsViewModel(
            getFavoriteNewsUseCase = GetFavoritesUseCase(taskRepository),
            deleteFavoriteNewsUseCase = DeleteFavoriteUseCase(taskRepository)
        )
    }

    /**
     * Creates an instance of [FindNewsViewModel] with its required dependencies.
     *
     * @return A new [FindNewsViewModel] instance.
     */
    fun getFindNewsViewModel(): FindNewsViewModel {
        val taskRepository = DataModule.getNewsRepository()

        return FindNewsViewModel(
            searchNewsUseCase = SearchNewsUseCase(taskRepository),
            saveFavoriteUseCase = SaveFavoriteUseCase(taskRepository)
        )
    }
}