package com.techand.news.ui.home

import androidx.lifecycle.*
import com.techand.news.data.repository.MainRepository
import com.techand.news.utils.Resource
import kotlinx.coroutines.Dispatchers
import java.text.SimpleDateFormat
import java.util.*


class HomeViewModel(private val mainRepository: MainRepository) : ViewModel() {


    var _name= MutableLiveData<String>()
    var name: LiveData<String> = _name

    var todayDate: String

    init {
        todayDate = getDate()
    }

    fun getNews(s:String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getLatest(s, todayDate).articles))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getDate(): String {
        var simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        return simpleDateFormat.format(Calendar.getInstance().time)
    }

}
