package com.example.mvvmglobalnewsapp.ui.home

import android.content.Intent
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
import com.example.mvvmglobalnewsapp.ui.articleContent.ArticleContentActivity
import com.example.mvvmglobalnewsapp.utils.Resource

const val ARTICLE_URL_TO_IMAGE = "articleUrlToImage"
const val ARTICLE_TITLE = "articleTitle"
const val ARTICLE_CONTENT = "articleContent"
const val ARTICLE = "article"

open class BaseFragment : Fragment() {

    protected lateinit var mainViewModel: MainViewModel

    private lateinit var recyclerView: RecyclerView
    lateinit var newsAdapter: NewsAdapter

    private lateinit var progressBar: ProgressBar

    private lateinit var baseIntent: Intent

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
        baseIntent = Intent(requireContext(), ArticleContentActivity().javaClass)
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
                baseIntent.putExtra(ARTICLE, newsAdapter.articlesList.get(position))
                startActivity(baseIntent)
            }
        })
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

    private fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

}