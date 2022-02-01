package com.example.mvvmglobalnewsapp.ui.home.business

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.mvvmglobalnewsapp.ui.MainActivity
import com.example.mvvmglobalnewsapp.ui.MainViewModel
import com.example.mvvmglobalnewsapp.ui.home.BaseFragment

class BusinessFragment : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = (activity as MainActivity).viewModel

        mainViewModel.getBusinessBreakingNews("us", "business")

        mainViewModel.businessBreakingNews.observe(viewLifecycleOwner, Observer {
            handleResponse(it)
        })
    }

}


