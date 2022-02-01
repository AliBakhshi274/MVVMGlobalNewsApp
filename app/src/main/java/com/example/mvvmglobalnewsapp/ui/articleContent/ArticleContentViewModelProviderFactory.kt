package com.example.mvvmglobalnewsapp.ui.articleContent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmglobalnewsapp.repository.NewsRepository

class ArticleContentViewModelProviderFactory(val newsRepository: NewsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ArticleContentViewModel(newsRepository) as T
    }
}