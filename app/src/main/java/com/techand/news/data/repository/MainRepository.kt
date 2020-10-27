package com.techand.news.data.repository

import com.techand.news.data.remote.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getLatest(name: String, date: String) = apiHelper.getLatest(name,date)

}