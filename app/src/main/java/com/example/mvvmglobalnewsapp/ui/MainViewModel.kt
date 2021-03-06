package com.example.mvvmglobalnewsapp.ui

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mvvmglobalnewsapp.NewsApplication
import com.example.mvvmglobalnewsapp.models.NewsResponse
import com.example.mvvmglobalnewsapp.repository.NewsRepository
import com.example.mvvmglobalnewsapp.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class MainViewModel(
    app: Application,
    val newsRepository: NewsRepository
) : AndroidViewModel(app) {

    // general BreakingNews
    val generalBreakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var generalBreakingNewsPage = 1
    var generalBreakingNewsResponse: NewsResponse? = null

    // sports BreakingNews
    val sportsBreakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var sportsBreakingNewsPage = 1
    var sportsBreakingNewsResponse: NewsResponse? = null

    // health BreakingNews
    val healthBreakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var healthBreakingNewsPage = 1
    var healthBreakingNewsResponse: NewsResponse? = null

    // science BreakingNews
    val scienceBreakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var scienceBreakingNewsPage = 1
    var scienceBreakingNewsResponse: NewsResponse? = null

    // entertainment BreakingNews
    val entertainmentBreakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var entertainmentBreakingNewsPage = 1
    var entertainmentBreakingNewsResponse: NewsResponse? = null

    // business BreakingNews
    val businessBreakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var businessBreakingNewsPage = 1
    var businessBreakingNewsResponse: NewsResponse? = null

    // technology BreakingNews
    val technologyBreakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var technologyBreakingNewsPage = 1
    var technologyBreakingNewsResponse: NewsResponse? = null

    fun getGeneralBreakingNews(countryCode: String, category: String) = viewModelScope.launch {
        safeBreakingNewsCall(countryCode, category)

    }

    fun getSportsBreakingNews(countryCode: String, category: String) = viewModelScope.launch {
        sportsBreakingNews.postValue(Resource.Loading())
        val response: Response<NewsResponse> =
            newsRepository.getBreakingNews(countryCode, category, sportsBreakingNewsPage)
        sportsBreakingNews.postValue(handleSportsBreakingNewsResponse(response))
    }

    fun getHealthBreakingNews(countryCode: String, category: String) = viewModelScope.launch {
        healthBreakingNews.postValue(Resource.Loading())
        val response: Response<NewsResponse> =
            newsRepository.getBreakingNews(countryCode, category, healthBreakingNewsPage)
        healthBreakingNews.postValue(handleHealthBreakingNewsResponse(response))
    }

    fun getEntertainmentBreakingNews(countryCode: String, category: String) =
        viewModelScope.launch {
            entertainmentBreakingNews.postValue(Resource.Loading())
            val response: Response<NewsResponse> =
                newsRepository.getBreakingNews(countryCode, category, entertainmentBreakingNewsPage)
            entertainmentBreakingNews.postValue(handleEntertainmentBreakingNewsResponse(response))
        }

    fun getScienceBreakingNews(countryCode: String, category: String) = viewModelScope.launch {
        scienceBreakingNews.postValue(Resource.Loading())
        val response: Response<NewsResponse> =
            newsRepository.getBreakingNews(countryCode, category, scienceBreakingNewsPage)
        scienceBreakingNews.postValue(handleScienceBreakingNewsResponse(response))
    }

    fun getBusinessBreakingNews(countryCode: String, category: String) = viewModelScope.launch {
        businessBreakingNews.postValue(Resource.Loading())
        val response: Response<NewsResponse> =
            newsRepository.getBreakingNews(countryCode, category, businessBreakingNewsPage)
        businessBreakingNews.postValue(handleBusinessBreakingNewsResponse(response))
    }

    fun getTechnologyBreakingNews(countryCode: String, category: String) = viewModelScope.launch {
        technologyBreakingNews.postValue(Resource.Loading())
        val response: Response<NewsResponse> =
            newsRepository.getBreakingNews(countryCode, category, technologyBreakingNewsPage)
        technologyBreakingNews.postValue(handleTechnologyBreakingNewsResponse(response))
    }

    private fun handleGeneralBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                generalBreakingNewsPage++
                if (generalBreakingNewsResponse == null) {
                    generalBreakingNewsResponse = resultResponse
                } else {
                    val oldArticles = generalBreakingNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(generalBreakingNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSportsBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                sportsBreakingNewsPage++
                if (sportsBreakingNewsResponse == null) {
                    sportsBreakingNewsResponse = resultResponse
                } else {
                    val oldArticles = sportsBreakingNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(sportsBreakingNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleHealthBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                healthBreakingNewsPage++
                if (healthBreakingNewsResponse == null) {
                    healthBreakingNewsResponse = resultResponse
                } else {
                    val oldArticles = healthBreakingNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(healthBreakingNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleEntertainmentBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                entertainmentBreakingNewsPage++
                if (entertainmentBreakingNewsResponse == null) {
                    entertainmentBreakingNewsResponse = resultResponse
                } else {
                    val oldArticles = entertainmentBreakingNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(entertainmentBreakingNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleScienceBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                scienceBreakingNewsPage++
                if (scienceBreakingNewsResponse == null) {
                    scienceBreakingNewsResponse = resultResponse
                } else {
                    val oldArticles = scienceBreakingNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(scienceBreakingNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleBusinessBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                businessBreakingNewsPage++
                if (businessBreakingNewsResponse == null) {
                    businessBreakingNewsResponse = resultResponse
                } else {
                    val oldArticles = businessBreakingNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(businessBreakingNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleTechnologyBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                technologyBreakingNewsPage++
                if (technologyBreakingNewsResponse == null) {
                    technologyBreakingNewsResponse = resultResponse
                } else {
                    val oldArticles = technologyBreakingNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(technologyBreakingNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


    private suspend fun safeBreakingNewsCall(countryCode: String, category: String){
        generalBreakingNews.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()){
                val response: Response<NewsResponse> =
                    newsRepository.getBreakingNews(countryCode, category, generalBreakingNewsPage)
                    generalBreakingNews.postValue(handleGeneralBreakingNewsResponse(response))
            }else{
                generalBreakingNews.postValue(Resource.Error("No Internet Connection"))
            }
        }catch (t: Throwable){
            when(t){
                is IOException -> generalBreakingNews.postValue(Resource.Error("Internet Failure"))
                else -> generalBreakingNews.postValue(Resource.Error("Conversion Error"))
            }
        }
    }


    fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<NewsApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }
        else {
            connectivityManager.activeNetworkInfo?.run {
                return when (type) {
                    TYPE_WIFI -> true
                    TYPE_MOBILE -> true
                    TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }

}


































