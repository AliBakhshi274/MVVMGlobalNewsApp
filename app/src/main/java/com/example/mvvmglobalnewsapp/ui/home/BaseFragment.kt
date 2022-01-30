package com.example.mvvmglobalnewsapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmglobalnewsapp.R
import com.example.mvvmglobalnewsapp.adapters.NewsAdapter
import com.example.mvvmglobalnewsapp.models.NewsResponse
import com.example.mvvmglobalnewsapp.ui.MainViewModel
import com.example.mvvmglobalnewsapp.utils.Resource

open class BaseFragment : Fragment() {

    protected lateinit var mainViewModel: MainViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapter

    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        progressBar = view.findViewById(R.id.progressBar_loadingIndicator)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

    }

    protected fun handleResponse(resource: Resource<NewsResponse>) {
        when (resource) {
            is Resource.Success -> {
                hideProgressBar()
                resource.data?.let { newsResponse ->
                    newsAdapter.differ.submitList(newsResponse.articles)
                }
            }
            is Resource.Error -> {
                hideProgressBar()
                resource.message?.let { message ->
                    Log.e("LOG_TAG", "An error occured: $message")
                }
            }
            is Resource.Loading -> {
                showProgressBar()
            }
        }
    }

    protected fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
    }

    protected fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    protected fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        recyclerView.apply {
            adapter = newsAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
        }
    }

}