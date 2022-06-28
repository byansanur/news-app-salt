package com.salt.newsappsalt.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.salt.newsappsalt.data.remote.ApiNewsServices
import com.salt.newsappsalt.data.remote.dto.Article
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PagingNewsAdapter @Inject constructor(
    private val apiNewsServices: ApiNewsServices,
    private val country: String,
    private val pageSize: Int
) : PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val page = params.key ?: 0
            val response = apiNewsServices.getTopHeadlines(country, page, pageSize)
            val nextKey =
                if (response.articles.isNotEmpty()) page + 1
                else null
            LoadResult.Page(
                data = response.articles,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (io: IOException) {
            LoadResult.Error(io)
        } catch (httpEx: HttpException) {
            LoadResult.Error(httpEx)
        }
    }
}