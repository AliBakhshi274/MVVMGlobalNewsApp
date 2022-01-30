package com.example.mvvmglobalnewsapp.ui.home.entertainment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.mvvmglobalnewsapp.ui.MainActivity
import com.example.mvvmglobalnewsapp.ui.home.BaseFragment

class EntertainmentFragment : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = (activity as MainActivity).viewModel

        mainViewModel.getEntertainmentBreakingNews("us", "entertainment")

        mainViewModel.entertainmentBreakingNews.observe(viewLifecycleOwner, Observer {
            handleResponse(it)
        })
    }

}