package com.example.mvvmglobalnewsapp.ui.home.business

import android.os.Bundle
import android.provider.SyncStateContract
import android.view.View
import android.widget.AbsListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmglobalnewsapp.ui.MainActivity
import com.example.mvvmglobalnewsapp.ui.MainViewModel
import com.example.mvvmglobalnewsapp.ui.home.BaseFragment
import com.example.mvvmglobalnewsapp.utils.Constants
import kotlin.properties.Delegates

class BusinessFragment : BaseFragment() {

    private lateinit var sListener: RecyclerView.OnScrollListener

    private var isLoading by Delegates.notNull<Boolean>()
    private var isLastPage by Delegates.notNull<Boolean>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = (activity as MainActivity).viewModel

        mainViewModel.getBusinessBreakingNews("us", Constants.BUSINESS)
        sListener = pagination()
        recyclerView.addOnScrollListener(this@BusinessFragment.sListener)

        mainViewModel.businessBreakingNews.observe(viewLifecycleOwner, Observer {
            handleResponse(it)
        })
    }

    private fun pagination(): RecyclerView.OnScrollListener {
        isLoading = false
        isLastPage = false
        var isScrolling = false

        val scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount

                val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
                val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
                val isNotAtBeginning = firstVisibleItemPosition >= 0
                val isTotalMoreThanVisible = totalItemCount >= Constants.QUERY_PAGE_SIZE
                val shouldPaginate =
                    isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning && isTotalMoreThanVisible && isScrolling
                if (shouldPaginate) {
                    mainViewModel.getBusinessBreakingNews("us", "business")
                    isScrolling = false
                } else {
                    recyclerView.setPadding(0, 0, 0, 0)
                }
            }
        }
        return scrollListener
    }

    override fun hideProgressBar() {
        super.hideProgressBar()
        isLoading = false
    }

    override fun showProgressBar() {
        super.showProgressBar()
        isLoading = true
    }

}


