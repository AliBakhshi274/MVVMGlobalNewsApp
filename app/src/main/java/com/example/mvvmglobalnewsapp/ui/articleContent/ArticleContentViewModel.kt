package com.example.mvvmglobalnewsapp.ui.articleContent

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmglobalnewsapp.models.Article
import com.example.mvvmglobalnewsapp.repository.NewsRepository
import kotlinx.coroutines.launch

class ArticleContentViewModel(val newsRepository: NewsRepository) : ViewModel() {

    fun saveArticle(article: Article) = viewModelScope.launch {
        val num = newsRepository.upsert(article)
        Log.e("myTag", "number : $num")
    }

}