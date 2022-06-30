package com.salt.newsappsalt.presentation.detail

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.salt.newsappsalt.base.BaseFragment
import com.salt.newsappsalt.databinding.FragmentNewsDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NewsDetailFragment : BaseFragment<FragmentNewsDetailBinding>() {

    private val args: NewsDetailFragmentArgs by navArgs()




    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNewsDetailBinding? {
        return FragmentNewsDetailBinding.inflate(inflater, container, false)
    }

    override fun initView() {

        Log.e("TAG", "initView: ${args.details}")

        binding?.apply {
            if (args.details != null) {
                Glide.with(requireContext())
                    .load(args.details?.urlToImage)
                    .into(appBarImage)

                tvTitleNewsBody.text = args.details?.title
                tvDescriptionNews.text = "${args.details?.description}. ${args.details?.content}"

                tvAuthor.text = "${args.details?.author}\n${args.details?.source?.name}"

                btnToWebView.setOnClickListener {
                    try {
                        val nav  = NewsDetailFragmentDirections
                            .actionNewsDetailFragmentToWebViewFragment(
                                args.details?.url
                            )
                        findNavController().navigate(nav)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                }
            }

        }


    }

}