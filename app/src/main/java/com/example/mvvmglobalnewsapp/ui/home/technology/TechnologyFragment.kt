package com.example.mvvmglobalnewsapp.ui.home.technology

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.mvvmglobalnewsapp.ui.MainActivity
import com.example.mvvmglobalnewsapp.ui.home.BaseFragment

class TechnologyFragment : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = (activity as MainActivity).viewModel

        mainViewModel.gettechnologyBreakingNews("us", "technology")

        mainViewModel.technologyBreakingNews.observe(viewLifecycleOwner, Observer {
            handleResponse(it)
        })
    }

}