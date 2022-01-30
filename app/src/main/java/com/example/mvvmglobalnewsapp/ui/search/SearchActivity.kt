package com.example.mvvmglobalnewsapp.ui.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmglobalnewsapp.R
import com.example.mvvmglobalnewsapp.adapters.NewsAdapter
import com.example.mvvmglobalnewsapp.database.ArticleDatabase
import com.example.mvvmglobalnewsapp.models.NewsResponse
import com.example.mvvmglobalnewsapp.repository.NewsRepository
import com.example.mvvmglobalnewsapp.utils.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchActivity : AppCompatActivity() {

    // mvvmSkeleton
    private lateinit var searchViewModel: SearchViewModel

    private lateinit var searchRecyclerView: RecyclerView
    private lateinit var searchNewsAdapter: NewsAdapter

    private lateinit var searchProgressBar: ProgressBar
    private lateinit var searchNewsEditText: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        searchRecyclerView = findViewById(R.id.recyclerView_search_activity)
        searchProgressBar = findViewById(R.id.progressBar_loadingIndicator_search_activity)
        searchNewsEditText = findViewById(R.id.search_news)

        mvvmSkeleton()
        setupRecyclerView(this.applicationContext)
        editTextChangeListener()

        searchViewModel.searchNews.observe(this, Observer {
            handleResponse(it)
        })

    }

    private fun mvvmSkeleton() {
        val newsRepository = NewsRepository(ArticleDatabase(this))
        val searchViewModelProviderFactory = SearchViewModelProviderFactory(newsRepository)
        searchViewModel =
            ViewModelProvider(this, searchViewModelProviderFactory).get(SearchViewModel::class.java)
    }

    private fun setupRecyclerView(context: Context) {
        searchNewsAdapter = NewsAdapter()
        searchRecyclerView.apply {
            adapter = searchNewsAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun editTextChangeListener() {
        var job: Job? = null
        searchNewsEditText.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(1000L)
                editable?.let {
                    if (editable.toString().isNotEmpty()){
                        searchViewModel.searchForNews(editable.toString())
                    }
                }
            }
        }
    }

    private fun handleResponse(resource: Resource<NewsResponse>) {
        when (resource) {
            is Resource.Success -> {
                hideProgressBar()
                resource.data?.let { newsResponse ->
                    searchNewsAdapter.differ.submitList(newsResponse.articles)
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
        searchProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        searchProgressBar.visibility = View.VISIBLE
    }

}




























