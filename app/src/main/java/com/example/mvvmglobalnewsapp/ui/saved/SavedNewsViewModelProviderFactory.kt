package com.example.mvvmglobalnewsapp.ui.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmglobalnewsapp.repository.NewsRepository

class SavedNewsViewModelProviderFactory(
    val newsRepository: NewsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SavedNewsViewModel(newsRepository) as T
    }
}