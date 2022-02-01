package com.example.mvvmglobalnewsapp.ui.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmglobalnewsapp.models.Article
import com.example.mvvmglobalnewsapp.repository.NewsRepository
import kotlinx.coroutines.launch

class SavedNewsViewModel(val newsRepository: NewsRepository) : ViewModel() {

    fun getSavedNews() = newsRepository.getSavedNews()

    fun deleteArticle(article: Article) = viewModelScope.launch {
        newsRepository.deleteArticle(article)
    }

}