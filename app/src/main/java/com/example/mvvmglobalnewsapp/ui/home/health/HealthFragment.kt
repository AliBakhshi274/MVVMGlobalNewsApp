package com.example.mvvmglobalnewsapp.ui.home.health

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.mvvmglobalnewsapp.ui.MainActivity
import com.example.mvvmglobalnewsapp.ui.home.BaseFragment

class HealthFragment : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = (activity as MainActivity).viewModel

        mainViewModel.getHealthBreakingNews("us", "health")

        mainViewModel.healthBreakingNews.observe(viewLifecycleOwner, Observer {
            handleResponse(it)
        })
    }

}