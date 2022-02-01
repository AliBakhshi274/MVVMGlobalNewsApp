package com.example.mvvmglobalnewsapp.ui.search

import android.content.Context
import android.content.Intent
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
import com.example.mvvmglobalnewsapp.ui.articleContent.ArticleContentActivity
import com.example.mvvmglobalnewsapp.ui.home.ARTICLE_CONTENT
import com.example.mvvmglobalnewsapp.ui.home.ARTICLE_TITLE
import com.example.mvvmglobalnewsapp.ui.home.ARTICLE_URL_TO_IMAGE
import com.example.mvvmglobalnewsapp.utils.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchActivity : AppCompatActivity() {

    // MvvmSkeleton
    private lateinit var searchViewModel: SearchViewModel

    // Features
    private lateinit var searchRecyclerView: RecyclerView
    private lateinit var searchNewsAdapter: NewsAdapter

    private lateinit var searchProgressBar: ProgressBar
    private lateinit var searchNewsEditText: EditText

    private lateinit var searchIntent: Intent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        init()
        hideProgressBar()
        mvvmSkeleton()
        setupRecyclerView(this.applicationContext)
        editTextChangeListener()
        itemClickListener()

        searchViewModel.searchNews.observe(this, Observer {
            handleResponse(it)
        })

    }

    private fun init(){
        searchNewsEditText = findViewById(R.id.search_news)
        searchRecyclerView = findViewById(R.id.recyclerView_search_activity)
        searchProgressBar = findViewById(R.id.progressBar_loadingIndicator_search_activity)
        searchIntent = Intent(this, ArticleContentActivity().javaClass)
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
                delay(500L)
                editable?.let {
                    if (editable.toString().isNotEmpty()){
                        searchViewModel.searchForNews(editable.toString())
                    }
                }
            }
        }
    }

    private fun itemClickListener() {
        searchNewsAdapter.setOnItemClickListener(object : NewsAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                searchIntent.putExtra(ARTICLE_URL_TO_IMAGE, searchNewsAdapter.articlesList.get(position).urlToImage)
                searchIntent.putExtra(ARTICLE_TITLE, searchNewsAdapter.articlesList.get(position).title)
                searchIntent.putExtra(ARTICLE_CONTENT, searchNewsAdapter.articlesList.get(position).content)
                startActivity(searchIntent)
            }
        })
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




























