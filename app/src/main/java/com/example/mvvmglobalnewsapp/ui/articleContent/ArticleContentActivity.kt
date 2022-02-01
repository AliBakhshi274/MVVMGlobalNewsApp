package com.example.mvvmglobalnewsapp.ui.articleContent

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mvvmglobalnewsapp.R
import com.example.mvvmglobalnewsapp.database.ArticleDatabase
import com.example.mvvmglobalnewsapp.models.Article
import com.example.mvvmglobalnewsapp.repository.NewsRepository
import com.example.mvvmglobalnewsapp.ui.home.ARTICLE
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class ArticleContentActivity : AppCompatActivity() {

    private lateinit var viewModel: ArticleContentViewModel

    private lateinit var textViewTitle: TextView
    private lateinit var textViewContent: TextView
    private lateinit var imageViewUrlToImage: ImageView
    private lateinit var fab: FloatingActionButton

    private lateinit var urlToImage: String
    private lateinit var title: String
    private lateinit var content: String
    private lateinit var article: Article

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_content)

        init()
        mvvmSkeleton()
        retrieveArgs()
        setValueBasicLevel()
        setValueUiLevel()
        fabClickListener()

    }

    private fun init() {
        textViewTitle = this.findViewById(R.id.textView_article_title)
        textViewContent = this.findViewById(R.id.textView_article_content)
        imageViewUrlToImage = this.findViewById(R.id.imageView_article_urlToImage)
        fab = this.findViewById(R.id.fab)
    }

    private fun mvvmSkeleton() {
        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = ArticleContentViewModelProviderFactory(newsRepository)
        viewModel =
            ViewModelProvider(
                this,
                viewModelProviderFactory
            ).get(ArticleContentViewModel::class.java)
    }

    private fun retrieveArgs() {
        article = intent.getSerializableExtra(ARTICLE) as Article
    }

    private fun setValueBasicLevel() {
        urlToImage = article.urlToImage.toString()
        title = article.title.toString()
        content = article.content.toString()
    }

    private fun setValueUiLevel() {
        textViewTitle.text = title
        textViewContent.text = content
        Glide.with(this).load(urlToImage).into(imageViewUrlToImage)
    }

    private fun fabClickListener() {
        fab.setOnClickListener {
            viewModel.saveArticle(article)
            Snackbar.make(findViewById(android.R.id.content), "Article Saved!", Snackbar.LENGTH_LONG).show()
        }
    }
}


























