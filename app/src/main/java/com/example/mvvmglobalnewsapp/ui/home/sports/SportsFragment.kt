package com.example.mvvmglobalnewsapp.ui.home.sports

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.mvvmglobalnewsapp.ui.MainActivity
import com.example.mvvmglobalnewsapp.ui.home.BaseFragment

class SportsFragment : BaseFragment() {

    val sports: String = "sports"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = (activity as MainActivity).viewModel

        mainViewModel.getSportsBreakingNews("us", "sports")

        mainViewModel.sportsBreakingNews.observe(viewLifecycleOwner, Observer {
            handleResponse(it)
        })
    }

}