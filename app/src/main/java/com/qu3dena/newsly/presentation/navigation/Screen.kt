package com.qu3dena.newsly.presentation.navigation

/**
 * Defines the navigation destinations (screens) available in the Newsly app.
 *
 * @property route The unique navigation route string for each screen.
 */
sealed class Screen(val route: String) {
    /**
     * The screen for searching or finding news articles.
     */
    data object FindNews : Screen("find_news_screen")

    /**
     * The screen displaying the user's favorite news articles.
     */
    data object FavoriteNews : Screen("favorite_news_screen")
}