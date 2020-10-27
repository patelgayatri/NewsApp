package com.techand.news.data.remote

import com.techand.news.data.model.News
import retrofit2.http.Query

interface NewsDataSource {

    suspend fun getLatest(@Query("q") q: String, @Query("from") date: String): News

}