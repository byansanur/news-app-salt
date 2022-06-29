package com.salt.newsappsalt.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.salt.newsappsalt.R
import com.salt.newsappsalt.base.BaseFragment
import com.salt.newsappsalt.databinding.FragmentNewsDetailBinding
import com.salt.newsappsalt.databinding.FragmentTopHeadlineBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailFragment : BaseFragment<FragmentNewsDetailBinding>() {
    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNewsDetailBinding {
        return FragmentNewsDetailBinding.inflate(inflater, container, false)
    }

    override fun initView() {

    }

}