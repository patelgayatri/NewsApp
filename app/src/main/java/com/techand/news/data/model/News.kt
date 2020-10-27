package com.techand.news.data.model


data class News(
    var status: String,
    var totalResults: Int,
    var articles: List<Article>
)


