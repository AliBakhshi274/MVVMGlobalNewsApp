package com.example.mvvmglobalnewsapp.ui.home.science

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.mvvmglobalnewsapp.ui.MainActivity
import com.example.mvvmglobalnewsapp.ui.home.BaseFragment


class ScienceFragment : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = (activity as MainActivity).viewModel

        mainViewModel.getScienceBreakingNews("us", "science")

        mainViewModel.scienceBreakingNews.observe(viewLifecycleOwner, Observer {
            handleResponse(it)
        })
    }

}