package com.example.mvvmglobalnewsapp.ui.home.general

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.mvvmglobalnewsapp.R
import com.example.mvvmglobalnewsapp.adapters.NewsAdapter
import com.example.mvvmglobalnewsapp.models.Article
import com.example.mvvmglobalnewsapp.ui.MainActivity
import com.example.mvvmglobalnewsapp.ui.home.BaseFragment


class GeneralFragment : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = (activity as MainActivity).viewModel

        mainViewModel.getGeneralBreakingNews("us", "general")

        mainViewModel.generalBreakingNews.observe(viewLifecycleOwner, Observer {
            handleResponse(it)
        })
    }
}



































