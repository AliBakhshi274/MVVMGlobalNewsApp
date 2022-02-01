package com.example.mvvmglobalnewsapp.ui.articleContent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmglobalnewsapp.models.Article
import com.example.mvvmglobalnewsapp.repository.NewsRepository
import kotlinx.coroutines.launch

class ArticleContentViewModel(val newsRepository: NewsRepository) : ViewModel() {

    fun saveArticle(article: Article) = viewModelScope.launch {
        newsRepository.upsert(article)
    }

}