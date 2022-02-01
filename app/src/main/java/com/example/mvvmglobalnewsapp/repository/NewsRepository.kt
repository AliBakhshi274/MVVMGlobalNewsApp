package com.example.mvvmglobalnewsapp.repository

import com.example.mvvmglobalnewsapp.api.newsApi
import com.example.mvvmglobalnewsapp.database.ArticleDatabase
import com.example.mvvmglobalnewsapp.models.Article

class NewsRepository(
    val db: ArticleDatabase
) {
    suspend fun getBreakingNews(countryCode: String, category: String, pageNumber: Int) =
        newsApi.retrofitService.getBreakingNews(countryCode, category, pageNumber)

    suspend fun searchForNews(searchQuery: String, pageNumber: Int) =
        newsApi.retrofitService.searchForNews(searchQuery, pageNumber)

    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)

}