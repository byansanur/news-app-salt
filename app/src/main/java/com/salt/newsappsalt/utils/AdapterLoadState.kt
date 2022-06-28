package com.salt.newsappsalt.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.salt.newsappsalt.databinding.ItemLoadingRvBinding

class AdapterLoadState(private val retry: () -> Unit) : LoadStateAdapter<AdapterLoadState.LoadStateHolder>() {
    inner class LoadStateHolder(private val binding: ItemLoadingRvBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(loadState: LoadState) {
                binding.apply {
                    pbLoad.isVisible = loadState is LoadState.Loading
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateHolder {
        val binding = ItemLoadingRvBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LoadStateHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadStateHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
}