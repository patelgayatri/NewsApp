package com.techand.news.data.remote

class ApiHelper(private val apiService: ApiService) {

    suspend fun getLatest(name: String, date: String) = apiService.getLatest(name,date)
}