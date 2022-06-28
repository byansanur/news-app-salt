package com.salt.newsappsalt.presentation.view_model

import androidx.lifecycle.*
import androidx.paging.cachedIn
import androidx.paging.map
import com.salt.newsappsalt.domain.repository.NewsRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class NewsViewModels @Inject constructor(
    private val newsRepositoryImpl: NewsRepositoryImpl
) : ViewModel() {

    fun getTopHeadline(country: String, pageSize: Int) =
        newsRepositoryImpl.getTopHeadline(country, pageSize)
            .map { pagingData -> pagingData.map { it } }
            .cachedIn(viewModelScope)

}