package com.techand.news.data.repository

import com.techand.news.data.local.NewsDao
import com.techand.news.data.model.Article
import com.techand.news.data.model.News
import com.techand.news.data.remote.ApiService
import com.techand.news.data.remote.BaseDataSource
import retrofit2.Response
import javax.inject.Inject


class MainRepository @Inject constructor(
    private val remoteDataSource: ApiService,
    private val localDataSource: NewsDao
) : BaseDataSource() {


    suspend fun getLiveData(newsKeyword: String, todayDate: String): Response<News> {
        val result = remoteDataSource.getNews(newsKeyword, todayDate)
        return result
    }

    suspend fun insertArticles(articles: List<Article>?, newsKeyword: String) {
        for (item in articles!!) {
            item.category = newsKeyword
            localDataSource.insertNews(item)
        }
    }

    fun getLocalResult(newsKeyword: String): List<Article> {
        return localDataSource.getNews(newsKeyword)
    }


}