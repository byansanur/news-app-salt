package com.salt.newsappsalt.presentation.headline

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.salt.newsappsalt.base.BaseFragment
import com.salt.newsappsalt.data.remote.dto.Article
import com.salt.newsappsalt.databinding.FragmentTopHeadlineBinding
import com.salt.newsappsalt.presentation.view_model.NewsViewModels
import com.salt.newsappsalt.utils.ArticleClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentTopHeadline : BaseFragment<FragmentTopHeadlineBinding>(), ArticleClickListener {

    private val newsViewModels: NewsViewModels by viewModels()

    private lateinit var adapterNews: AdapterNews

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTopHeadlineBinding {
        return FragmentTopHeadlineBinding.inflate(inflater, container, false)
    }

    override fun initView() {

        adapterNews = AdapterNews(this)
        binding.apply {
            rvListNews.adapter = adapterNews
            rvListNews.layoutManager = LinearLayoutManager(requireContext())

        }

        var job: Job? = null
        job?.cancel()
        job = MainScope().launch {
            delay(1000)
            getArticleList()
        }
        onDataLoading()
        job.isCompleted

    }

    private fun getArticleList() {
        lifecycleScope.launch {
            newsViewModels.getTopHeadline("id", 10)
                .distinctUntilChanged()
                .collectLatest {
                    adapterNews.submitData(it)
                }
        }
        adapterNews.addLoadStateListener { loadState ->
            binding.apply {
                when(loadState.source.refresh) {
                    is LoadState.Loading -> {
                        onDataLoading()
                        Toast.makeText(context, "loading", Toast.LENGTH_SHORT).show()
                        Log.e("TAG", "getReposList: loadingState")
                    }
                    is LoadState.NotLoading -> {
                        onDataLoaded()
                        Log.e("TAG", "getReposList: notLoadingState")

                    }
                    is LoadState.Error -> {
                        onDataLoaded()
                        Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
                        Log.e("TAG", "getReposList: errorState")
                    }
                }
            }

        }
    }

    private fun onDataLoaded() {
        binding.pbLoad.visibility = View.INVISIBLE
    }

    private fun onDataLoading() {
        binding.pbLoad.visibility = View.VISIBLE
    }

    override fun onClick(search: Article) {

    }


}