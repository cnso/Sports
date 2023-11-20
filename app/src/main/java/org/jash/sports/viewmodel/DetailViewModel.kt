package org.jash.sports.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.jash.common.mvvm.BaseViewModel
import org.jash.common.utils.retrofit
import org.jash.sports.dao.NewsDao
import org.jash.sports.entry.News
import org.jash.sports.net.Service

class DetailViewModel(val dao:NewsDao):BaseViewModel() {
    val newsLiveData by lazy { MutableLiveData<News>() }
    val collectedLiveData by lazy { MutableLiveData<String>() }
    val collectsLiveData by lazy { MutableLiveData<List<Int>>() }
    val service by lazy { retrofit.create(Service::class.java) }

    fun loadNews(id:Int) {
        viewModelScope.launch {
            val news = dao.findById(id)
            newsLiveData.postValue(news)
            try{
                val res = service.getNewsDetail(id)
                if (res.code == 0) {
                    newsLiveData.postValue(res.data)
                    dao.insert(res.data)
                } else {
                    errorLiveData.postValue(res.msg)
                }
            } catch (e:Exception) {
                errorLiveData.postValue(e.message)
            }
        }
    }
    fun loadCollect() {
        viewModelScope.launch {
            try{
                val res = service.getMyCollect()
                if (res.code == 0) {
                    collectsLiveData.postValue(res.data.map { it.id })
                } else {
                    errorLiveData.postValue(res.msg)
                }
            } catch (e:Exception) {
                errorLiveData.postValue(e.message)
            }
        }
    }
    fun collect(nid:Int) {
        viewModelScope.launch {
            try{
                val res = service.collect(nid)
                if (res.code == 0) {
                    collectedLiveData.postValue(res.data)
                } else {
                    errorLiveData.postValue(res.msg)
                }
            } catch (e:Exception) {
                errorLiveData.postValue(e.message)
            }
        }
    }
}