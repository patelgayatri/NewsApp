package com.techand.news.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.techand.news.data.model.Article

@Dao
interface NewsDao {

    @Query("SELECT * FROM article where category = :newsKeyword")
    fun getNews(newsKeyword: String): List<Article>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(articles: Article) {

    }
}