package com.techand.news.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.techand.news.data.repository.MainRepository
import com.techand.news.utils.Resource
import kotlinx.coroutines.Dispatchers
import java.text.SimpleDateFormat
import java.util.*


class HomeViewModel @ViewModelInject constructor(private val mainRepository: MainRepository) :
    ViewModel() {

    private var todayDate: String

    init {
        todayDate = getDate()
    }

    private fun getDate(): String {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return simpleDateFormat.format(Calendar.getInstance().time)
    }

    fun getNews(newsKeyword: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        var result =mainRepository.getLocalResult(newsKeyword)
        emit(Resource.success(data = result))
        try {
            emit(Resource.success(data = mainRepository.getLiveData(newsKeyword,todayDate).body()?.articles))
            mainRepository.insertArticles(mainRepository.getLiveData(newsKeyword, todayDate).body()?.articles,newsKeyword)

        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}
