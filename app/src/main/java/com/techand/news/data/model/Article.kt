package com.techand.news.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article")
data class Article(
    val id: Int,
    var author: String,
    @PrimaryKey
    var title: String,
    var description: String,
    var url: String,
    var urlToImage: String,
    var publishedAt: String,
    var content: String,
    var category: String
) {
    data class Source(
        val name: String
    )
}