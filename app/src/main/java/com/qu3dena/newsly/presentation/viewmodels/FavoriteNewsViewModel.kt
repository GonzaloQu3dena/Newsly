package com.qu3dena.newsly.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qu3dena.newsly.domain.models.News
import com.qu3dena.newsly.domain.usecases.DeleteFavoriteUseCase
import com.qu3dena.newsly.domain.usecases.GetFavoritesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.invoke

data class FavoritesUiState(
    val isLoading: Boolean = false,
    val favorites: List<News> = emptyList(),
    val errorMessage: String? = null
)

class FavoriteNewsViewModel(
    private val getFavoriteNewsUseCase: GetFavoritesUseCase,
    private val deleteFavoriteNewsUseCase: DeleteFavoriteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoritesUiState())
    val uiState: StateFlow<FavoritesUiState> = _uiState.asStateFlow()

    init {
        loadFavorites()
    }

    private fun loadFavorites() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            getFavoriteNewsUseCase.invoke()
                .catch { e ->
                    _uiState.update { it.copy(isLoading = false, errorMessage = e.message) }
                }
                .collect { favorites ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            favorites = favorites,
                            errorMessage = null
                        )
                    }
                }
        }
    }

    fun onDeleteFavorite(news: News) {
        viewModelScope.launch {
            deleteFavoriteNewsUseCase.invoke(news)
                .catch { e ->
                    _uiState.update { it.copy(errorMessage = e.message) }
                }
                .collect()
        }
    }
}