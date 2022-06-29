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
    private lateinit var adapterBreakingNews: AdapterBreakingNews

    private var categories = ""

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTopHeadlineBinding {
        return FragmentTopHeadlineBinding.inflate(inflater, container, false)
    }

    override fun initView() {

        adapterNews = AdapterNews(this)
        adapterBreakingNews = AdapterBreakingNews(this)
        binding.apply {
            rvListBreakingNews.adapter = adapterBreakingNews
            rvListBreakingNews.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false,
            )
            rvListNews.adapter = adapterNews
            rvListNews.layoutManager = LinearLayoutManager(requireContext())

            swipeRefresh.setOnRefreshListener {
                jobCallData()
            }

        }
        jobCallData()

    }

    private fun jobCallData() {
        var job: Job? = null
        job?.cancel()
        job = MainScope().launch {
            delay(1000)
            getBreakingNews()
            getArticleCategoryList(categories)
        }
        binding.swipeRefresh.isRefreshing = true
        job.isCompleted
    }

    private fun getBreakingNews() {
        lifecycleScope.launch {
            newsViewModels.getBreakingNews("id", 3)
                .distinctUntilChanged()
                .collectLatest {
                    adapterBreakingNews.submitData(it)
                }
        }
        adapterBreakingNews.addLoadStateListener { loadState ->
            binding.apply {
                when(loadState.source.refresh) {
                    is LoadState.Loading -> {
                        swipeRefresh.isRefreshing = true
                        onDataLoading()
                        Toast.makeText(context, "loading", Toast.LENGTH_SHORT).show()
                        Log.e("TAG", "getReposList: loadingState")
                    }
                    is LoadState.NotLoading -> {
                        swipeRefresh.isRefreshing = false
                        onDataLoaded()
                        Log.e("TAG", "getReposList: notLoadingState")

                    }
                    is LoadState.Error -> {
                        swipeRefresh.isRefreshing = false
                        onDataLoaded()
                        Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
                        Log.e("TAG", "getReposList: errorState")
                    }
                }
            }
        }
    }

    private fun getArticleCategoryList(category: String) {
        lifecycleScope.launch {
            newsViewModels.getTopHeadline("id", category,5)
                .distinctUntilChanged()
                .collectLatest {
                    adapterNews.submitData(it)
                }
        }
        adapterNews.addLoadStateListener { loadState ->
            binding.apply {
                when(loadState.source.refresh) {
                    is LoadState.Loading -> {
                        swipeRefresh.isRefreshing = true
                        onDataLoading()
                        Toast.makeText(context, "loading", Toast.LENGTH_SHORT).show()
                        Log.e("TAG", "getReposList: loadingState")
                    }
                    is LoadState.NotLoading -> {
                        swipeRefresh.isRefreshing = false
                        onDataLoaded()
                        Log.e("TAG", "getReposList: notLoadingState")

                    }
                    is LoadState.Error -> {
                        swipeRefresh.isRefreshing = false
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

    override fun onClick(article: Article) {

    }


}