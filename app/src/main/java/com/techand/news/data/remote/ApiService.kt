package com.techand.news.data.remote

import com.techand.news.data.model.News
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("everything?language=en&sortBy=publishedAt&apiKey=f04eadcee820416f811e10cec4864b36")
    suspend fun getLatest(@Query("q") q: String,@Query("from") date: String): News



}