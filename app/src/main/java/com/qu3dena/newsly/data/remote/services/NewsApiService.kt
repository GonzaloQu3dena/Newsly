package com.qu3dena.newsly.data.remote.services

import com.qu3dena.newsly.data.remote.dto.NewsDto
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit service interface for accessing news articles from the remote API.
 */
interface NewsApiService {

    /**
     * Retrieves a list of news articles that match the provided description keyword.
     *
     * @param description The keyword used to filter news articles by their description.
     * @return A list of [NewsDto] objects matching the search criteria.
     */
    @GET("articles.php")
    suspend fun findNews(@Query("description") description: String): List<NewsDto>
}