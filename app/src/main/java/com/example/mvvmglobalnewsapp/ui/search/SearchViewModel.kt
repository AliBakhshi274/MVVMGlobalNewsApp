package com.example.mvvmglobalnewsapp.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmglobalnewsapp.models.NewsResponse
import com.example.mvvmglobalnewsapp.repository.NewsRepository
import com.example.mvvmglobalnewsapp.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class SearchViewModel(val newsRepository: NewsRepository) : ViewModel() {

    // Search For News
    val searchNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchNewsPage = 1
    var searchNewsResponse: NewsResponse? = null


    fun searchForNews(searchQuery: String) = viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        val response: Response<NewsResponse> =
            newsRepository.searchForNews(searchQuery, searchNewsPage)
        searchNews.postValue(handleSearchNewsResponse(response))
    }

    private fun handleSearchNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}