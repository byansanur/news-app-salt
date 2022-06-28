package com.salt.newsappsalt.presentation.headline

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.salt.newsappsalt.data.remote.dto.Article
import com.salt.newsappsalt.databinding.ItemListNewsBinding
import com.salt.newsappsalt.utils.ArticleClickListener
import com.salt.newsappsalt.utils.DiffUtils.Companion.DIFF_UTIL_ARTICLE

class AdapterNews(
    private val clickListener: ArticleClickListener
) : PagingDataAdapter<Article, AdapterNews.Holder>(DIFF_UTIL_ARTICLE) {


    inner class Holder(private val binding: ItemListNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Article) {
            with(binding) {
                tvTittleNews.text = data.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterNews.Holder {
        val binding = ItemListNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: AdapterNews.Holder, position: Int) {
        val listItem = getItem(position)
        if (listItem != null) {
            holder.bind(listItem)
        }
    }

}