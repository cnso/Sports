package org.jash.sports

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jash.common.mvvm.BaseViewModel
import org.jash.common.utils.retrofit
import org.jash.sports.net.Service

class MainViewModel:BaseViewModel() {
    val progressLiveData by lazy { MutableLiveData<Int>() }
    val completeLiveData by lazy { MutableLiveData<String>() }
    val service by lazy { retrofit.create(Service::class.java) }
    fun start() {
        viewModelScope.launch {
            repeat(10) {
                progressLiveData.postValue(10 - it)
                delay(1000)
            }
            completeLiveData.postValue("完成")
        }
    }
}