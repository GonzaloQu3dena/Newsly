package com.qu3dena.newsly.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qu3dena.newsly.domain.models.News
import com.qu3dena.newsly.domain.usecases.SaveFavoriteUseCase
import com.qu3dena.newsly.domain.usecases.SearchNewsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class FindNewsUIState(
    val isLoading: Boolean = false,
    val newsList: List<News> = emptyList(),
    val errorMessage: String? = null
)

class FindNewsViewModel(
    private val searchNewsUseCase: SearchNewsUseCase,
    private val saveFavoriteUseCase: SaveFavoriteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(FindNewsUIState())
    val uiState: StateFlow<FindNewsUIState> = _uiState.asStateFlow()

    fun onSearch(keyword: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true, errorMessage = null)
            }
            try {
                searchNewsUseCase.invoke(keyword).collectLatest { list ->
                    _uiState.update {
                        it.copy(isLoading = false, newsList = list)
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, errorMessage = e.message)
                }
            }
        }
    }

    fun onToggleFavorite(news: News) {
        viewModelScope.launch {
            saveFavoriteUseCase.invoke(news.copy(isFavorite = !news.isFavorite)).collect()

            _uiState.update { state ->
                val updated = state.newsList.map {
                    if (it.title == news.title)
                        it.copy(isFavorite = !it.isFavorite)
                    else
                        it
                }
                state.copy(newsList = updated)
            }
        }
    }
}