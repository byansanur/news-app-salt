package com.salt.newsappsalt.presentation.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.salt.newsappsalt.R
import com.salt.newsappsalt.base.BaseFragment
import com.salt.newsappsalt.databinding.FragmentWebViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewFragment : BaseFragment<FragmentWebViewBinding>() {

    private val args: WebViewFragmentArgs by navArgs()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWebViewBinding {
        return FragmentWebViewBinding.inflate(inflater, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun initView() {

        binding.apply {
//            webView.setListener(activity, this)
            webView.setMixedContentAllowed(true)


            webView.loadUrl(args.url.toString())


//            webView.webViewClient = object : WebViewClient() {
//                override fun onPageFinished(view: WebView?, url: String?) {
//                    super.onPageFinished(view, url)
//                    view?.loadUrl("javascript:alert('Success load')")
//                }
//            }



//            webView.webChromeClient = object : WebChromeClient() {
//                override fun onJsAlert(
//                    view: WebView?,
//                    url: String?,
//                    message: String?,
//                    result: JsResult?
//                ): Boolean {
//                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
//                    result?.confirm()
//                    return true
//                }
//            }

        }
    }

    override fun onResume() {
        super.onResume()
        binding.webView.onResume()
    }

    override fun onDestroy() {
        binding.webView.onDestroy()
        super.onDestroy()
    }
}