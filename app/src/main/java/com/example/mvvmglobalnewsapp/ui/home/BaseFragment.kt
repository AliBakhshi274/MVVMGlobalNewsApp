package com.example.mvvmglobalnewsapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmglobalnewsapp.R
import com.example.mvvmglobalnewsapp.adapters.NewsAdapter
import com.example.mvvmglobalnewsapp.models.NewsResponse
import com.example.mvvmglobalnewsapp.ui.MainViewModel
import com.example.mvvmglobalnewsapp.ui.articleContent.ArticleContentActivity
import com.example.mvvmglobalnewsapp.utils.Constants
import com.example.mvvmglobalnewsapp.utils.Resource
import kotlin.properties.Delegates

const val ARTICLE = "article"

open class BaseFragment : Fragment() {

    //region Define Variables
    protected lateinit var mainViewModel: MainViewModel

    protected lateinit var recyclerView: RecyclerView
    lateinit var newsAdapter: NewsAdapter

    private lateinit var progressBar: ProgressBar

    private lateinit var baseFragmentIntent: Intent

    //endregion

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        init(view)
        itemClickListener()
        return view
    }

    private fun init(view: View) {
        recyclerView = view.findViewById(R.id.recyclerView)
        progressBar = view.findViewById(R.id.progressBar_loadingIndicator)
        baseFragmentIntent = Intent(requireContext(), ArticleContentActivity().javaClass)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        recyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun itemClickListener() {
        newsAdapter.setOnItemClickListener(object : NewsAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                baseFragmentIntent.putExtra(ARTICLE, newsAdapter.articlesList[position])
                startActivity(baseFragmentIntent)
            }
        })
    }

    protected fun handleResponse(resource: Resource<NewsResponse>) {
        when (resource) {
            is Resource.Success -> {
                hideProgressBar()
                resource.data?.let { newsResponse ->
                    newsAdapter.differ.submitList(newsResponse.articles.toList())
                }
            }
            is Resource.Error -> {
                hideProgressBar()
                resource.message?.let { message ->
                    Log.e("BaseFragment", "An error occured: $message")
                }
            }
            is Resource.Loading -> {
                showProgressBar()
            }
        }
    }

    protected open fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
    }

    protected open fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }




}













