package com.salt.newsappsalt.utils

import androidx.recyclerview.widget.DiffUtil
import com.salt.newsappsalt.data.remote.dto.Article

class DiffUtils {
    companion object {
        val DIFF_UTIL_ARTICLE = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem.author == newItem.author

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem == newItem

        }
    }
}