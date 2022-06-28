package com.salt.newsappsalt.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.salt.newsappsalt.data.remote.ApiNewsServices
import com.salt.newsappsalt.domain.paging.PagingNewsAdapter
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiNewsServices: ApiNewsServices
)  {

    fun getTopHeadline(country: String, pageSize: Int) =
        Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { PagingNewsAdapter(apiNewsServices, country, pageSize) }
        ).flow

}