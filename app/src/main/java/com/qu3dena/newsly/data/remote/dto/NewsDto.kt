package com.qu3dena.newsly.data.remote.dto

 import com.qu3dena.newsly.domain.models.News

 /**
  * Data Transfer Object representing a news article received from a remote source.
  *
  * @property author Name of the author of the article, or `null` if not available.
  * @property title Title of the news article.
  * @property description Brief summary or description of the article.
  * @property content Full content of the news article.
  * @property publishedAt Publication date and time in ISO 8601 format.
  * @property url URL linking to the full article, or `null` if not available.
  * @property urlToImage URL linking to an image associated with the article, or `null` if not available.
  * @property source The [SourceDto] representing the news source.
  */
 data class NewsDto(
     val author: String?,
     val title: String,
     val description: String,
     val content: String,
     val publishedAt: String,
     val url: String?,
     val urlToImage: String?,
     val source: SourceDto
 ) {
     /**
      * Maps this [NewsDto] to a domain [News] model.
      *
      * @return The corresponding [News] domain object.
      */
     fun toDomain(): News {
         return News(
             author = author,
             title = title,
             description = description,
             content = content,
             publishedAt = publishedAt,
             url = url,
             urlToImage = urlToImage,
             sourceName = source.name
         )
     }
 }

 /**
  * Data Transfer Object representing the source of a news article.
  *
  * @property id The unique identifier of the news source, or `null` if not available.
  * @property name The name of the news source.
  */
 data class SourceDto(
     val id: String?,
     val name: String
 )