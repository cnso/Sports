package org.jash.sports.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.jash.common.mvvm.BaseViewModel
import org.jash.sports.dao.NewsDao
import org.jash.sports.entry.News

class DetailViewModel(val dao:NewsDao):BaseViewModel() {
    val newsLiveData by lazy { MutableLiveData<News>() }
    fun loadNews(id:Int) {
        viewModelScope.launch {
            val news = dao.findById(id)
            newsLiveData.postValue(news)
        }
    }
}