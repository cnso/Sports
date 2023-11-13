package org.jash.sports.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jash.common.mvvm.BaseViewModel
import org.jash.common.utils.retrofit
import org.jash.sports.entry.Category
import org.jash.sports.net.Service

class MainViewModel:BaseViewModel() {
    val progressLiveData by lazy { MutableLiveData<Int>() }
    val completeLiveData by lazy { MutableLiveData<String>() }
    val categoryLiveData by lazy { MutableLiveData<List<Category>>() }
    val service by lazy { retrofit.create(Service::class.java) }
    fun start() {
        viewModelScope.launch {
            repeat(2) {
                progressLiveData.postValue(10 - it)
                delay(1000)
            }
            completeLiveData.postValue("完成")
        }
    }
    fun loadCategory() {
        viewModelScope.launch {
            try {
                val res = service.getAllCategory()
                if (res.code == 0) {
                    categoryLiveData.postValue(res.data)
                } else {
                    errorLiveData.postValue(res.msg)
                }
            } catch (e:Exception) {
                errorLiveData.postValue(e.message)
            }
        }
    }

}