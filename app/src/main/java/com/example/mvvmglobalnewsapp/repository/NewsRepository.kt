package com.example.mvvmglobalnewsapp.repository

import com.example.mvvmglobalnewsapp.api.newsApi
import com.example.mvvmglobalnewsapp.database.ArticleDatabase

class NewsRepository(
    val db: ArticleDatabase
) {
    suspend fun getBreakingNews(countryCode: String, category: String, pageNumber: Int) =
        newsApi.retrofitService.getBreakingNews(countryCode, category, pageNumber)
}