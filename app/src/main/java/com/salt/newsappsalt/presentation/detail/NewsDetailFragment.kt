package com.salt.newsappsalt.presentation.detail

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.salt.newsappsalt.base.BaseFragment
import com.salt.newsappsalt.databinding.FragmentNewsDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailFragment : BaseFragment<FragmentNewsDetailBinding>() {

    private val args: NewsDetailFragmentArgs by navArgs()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNewsDetailBinding {
        return FragmentNewsDetailBinding.inflate(inflater, container, false)
    }

    override fun initView() {

        Log.e("TAG", "initView: ${args.details}", )


    }

}