package com.example.mvvmglobalnewsapp.ui.saved

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmglobalnewsapp.R
import com.example.mvvmglobalnewsapp.adapters.NewsAdapter
import com.example.mvvmglobalnewsapp.database.ArticleDatabase
import com.example.mvvmglobalnewsapp.repository.NewsRepository
import com.example.mvvmglobalnewsapp.ui.articleContent.ArticleContentActivity
import com.example.mvvmglobalnewsapp.ui.home.ARTICLE
import com.google.android.material.snackbar.Snackbar

class SavedNewsActivity : AppCompatActivity() {

    private lateinit var viewModel: SavedNewsViewModel
    private lateinit var newsAdapter: NewsAdapter

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    private lateinit var savedIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_news)

        init()
        showProgressBar()
        mvvmSkeleton()
        setupRecyclerView()
        getSavedNews()
        itemClickListener()
        deleteItem(findViewById(android.R.id.content))

    }

    private fun deleteItem(view: View?) {
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article = newsAdapter.differ.currentList.get(position)
                viewModel.deleteArticle(article)
                Snackbar.make(view!!, "Successfully deleted article", Snackbar.LENGTH_LONG).apply {
                    setAction("Undo") {
                        viewModel.saveArticle(article)
                    }
                    show()
                }
            }
        }
        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(recyclerView)
        }
    }

    private fun getSavedNews() {
        hideProgressBar()
        viewModel.getSavedNews().observe(this, Observer { articles ->
            newsAdapter.differ.submitList(articles)
        })
    }

    private fun init() {
        savedIntent = Intent(this, ArticleContentActivity().javaClass)
        recyclerView = findViewById(R.id.recyclerview_savedNewsActivity)
        progressBar = findViewById(R.id.progressBar_loadingIndicator_savedNewsActivity)
    }

    private fun mvvmSkeleton() {
        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = SavedNewsViewModelProviderFactory(newsRepository)
        viewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(SavedNewsViewModel::class.java)
    }

    private fun itemClickListener() {
        newsAdapter.setOnItemClickListener(object : NewsAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                savedIntent.putExtra(ARTICLE, newsAdapter.articlesList.get(position))
                startActivity(savedIntent)
            }
        })
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        recyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(this@SavedNewsActivity)
        }
    }

}
























