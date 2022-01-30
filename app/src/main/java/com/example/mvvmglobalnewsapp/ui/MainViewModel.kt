package com.example.mvvmglobalnewsapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmglobalnewsapp.models.NewsResponse
import com.example.mvvmglobalnewsapp.repository.NewsRepository
import com.example.mvvmglobalnewsapp.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(val newsRepository: NewsRepository) : ViewModel() {

    // general BreakingNews
    val generalBreakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var generalBreakingNewsPage = 1

    // sports BreakingNews
    val sportsBreakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var sportsBreakingNewsPage = 1

    // health BreakingNews
    val healthBreakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val healthBreakingNewsPage = 1

    // science BreakingNews
    val scienceBreakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val scienceBreakingNewsPage = 1

    // entertainment BreakingNews
    val entertainmentBreakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val entertainmentBreakingNewsPage = 1

    // business BreakingNews
    val businessBreakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val businessBreakingNewsPage = 1

    // technology BreakingNews
    val technologyBreakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val technologyBreakingNewsPage = 1

    init {
//        getBreakingNews("us", "general")
    }

    fun gettechnologyBreakingNews(countryCode: String, category: String) = viewModelScope.launch {
        technologyBreakingNews.postValue(Resource.Loading())
        val response: Response<NewsResponse> =
            newsRepository.getBreakingNews(countryCode, category, technologyBreakingNewsPage)
        technologyBreakingNews.postValue(handleBreakingNewsResponse(response))
    }

    fun getBusinessBreakingNews(countryCode: String, category: String) = viewModelScope.launch {
        businessBreakingNews.postValue(Resource.Loading())
        val response: Response<NewsResponse> =
            newsRepository.getBreakingNews(countryCode, category, businessBreakingNewsPage)
        businessBreakingNews.postValue(handleBreakingNewsResponse(response))
    }

    fun getScienceBreakingNews(countryCode: String, category: String) = viewModelScope.launch {
        scienceBreakingNews.postValue(Resource.Loading())
        val response: Response<NewsResponse> =
            newsRepository.getBreakingNews(countryCode, category, scienceBreakingNewsPage)
        scienceBreakingNews.postValue(handleBreakingNewsResponse(response))
    }

    fun getEntertainmentBreakingNews(countryCode: String, category: String) = viewModelScope.launch {
        entertainmentBreakingNews.postValue(Resource.Loading())
        val response: Response<NewsResponse> =
            newsRepository.getBreakingNews(countryCode, category, entertainmentBreakingNewsPage)
        entertainmentBreakingNews.postValue(handleBreakingNewsResponse(response))
    }


    fun getHealthBreakingNews(countryCode: String, category: String) = viewModelScope.launch {
        healthBreakingNews.postValue(Resource.Loading())
        val response: Response<NewsResponse> =
            newsRepository.getBreakingNews(countryCode, category, healthBreakingNewsPage)
        healthBreakingNews.postValue(handleBreakingNewsResponse(response))
    }

    fun getGeneralBreakingNews(countryCode: String, category: String) = viewModelScope.launch {
        generalBreakingNews.postValue(Resource.Loading())
        val response: Response<NewsResponse> =
            newsRepository.getBreakingNews(countryCode, category, generalBreakingNewsPage)
        generalBreakingNews.postValue(handleBreakingNewsResponse(response))
    }

    fun getSportsBreakingNews(countryCode: String, category: String) = viewModelScope.launch {
        sportsBreakingNews.postValue(Resource.Loading())
        val response: Response<NewsResponse> =
            newsRepository.getBreakingNews(countryCode, category, sportsBreakingNewsPage)
        sportsBreakingNews.postValue(handleBreakingNewsResponse(response))
    }

    private fun handleBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}



























