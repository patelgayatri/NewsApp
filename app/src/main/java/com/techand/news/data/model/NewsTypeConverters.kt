package com.techand.news.data.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class NewsTypeConverters {
    var gson = Gson()

    @TypeConverter
    fun fromSource(value:Article.Source): String {
        val gson = Gson()
        val type = object : TypeToken<Article.Source>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toSource(value: String): Article.Source {
        val gson = Gson()
        val type = object : TypeToken<Article.Source>() {}.type
        return gson.fromJson(value, type)
    }
}
