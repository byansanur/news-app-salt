package com.salt.newsappsalt.data.remote.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NewsResponse(
    @SerializedName("articles")
    @Expose
    val articles: MutableList<Article>,
    @SerializedName("status")
    @Expose
    val status: String,
    @SerializedName("totalResults")
    @Expose
    val totalResults: Int,
    @SerializedName("code")
    @Expose
    val code: String? = null,
    @SerializedName("message")
    @Expose
    val message: String? = null
)

data class Article(
    @SerializedName("author")
    @Expose
    val author: String? = null,
    @SerializedName("content")
    @Expose
    val content: String? = null,
    @SerializedName("description")
    @Expose
    val description: String? = null,
    @SerializedName("publishedAt")
    @Expose
    val publishedAt: String? = null,
    @SerializedName("source")
    @Expose
    val source: Source? = null,
    @SerializedName("title")
    @Expose
    val title: String? = null,
    @SerializedName("url")
    @Expose
    val url: String? = null,
    @SerializedName("urlToImage")
    @Expose
    val urlToImage: String? = null
): Serializable

data class Source(
    @SerializedName("id")
    @Expose
    val id: String,
    @SerializedName("name")
    @Expose
    val name: String
)