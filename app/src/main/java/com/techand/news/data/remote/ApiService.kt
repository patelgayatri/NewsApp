package com.techand.news.data.remote

import com.techand.news.data.model.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("top-headlines?sortBy=popularity&language=en&apiKey=f04eadcee820416f811e10cec4864b36")
    suspend fun getNews(
        @Query("category") category: String,
        @Query("from") date: String
    ): Response<News>

}