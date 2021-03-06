package com.salt.newsappsalt.presentation.headline

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.salt.newsappsalt.R
import com.salt.newsappsalt.data.remote.dto.Article
import com.salt.newsappsalt.databinding.ItemListBreakingNewsBinding
import com.salt.newsappsalt.utils.ArticleClickListener
import com.salt.newsappsalt.utils.DiffUtils.Companion.DIFF_UTIL_ARTICLE

class AdapterBreakingNews(
    private val clickListener: ArticleClickListener
) : PagingDataAdapter<Article, AdapterBreakingNews.Holder>(DIFF_UTIL_ARTICLE) {


    inner class Holder(private val binding: ItemListBreakingNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Article) {
            with(binding) {
                Glide.with(binding.root.context)
                    .load(data.urlToImage)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_round_warning_24)
                    .into(imgUrl)
                tvTitleNews.text = data.title

                root.setOnClickListener {
                    clickListener.onClick(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterBreakingNews.Holder {
        val binding = ItemListBreakingNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: AdapterBreakingNews.Holder, position: Int) {
        val listItem = getItem(position)
        if (listItem != null) {
            holder.bind(listItem)
        }
    }

}