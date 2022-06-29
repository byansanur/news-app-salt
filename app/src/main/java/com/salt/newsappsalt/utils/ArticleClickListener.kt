package com.salt.newsappsalt.utils

import com.salt.newsappsalt.data.remote.dto.Article

interface ArticleClickListener {
    fun onClick(article: Article?)
}