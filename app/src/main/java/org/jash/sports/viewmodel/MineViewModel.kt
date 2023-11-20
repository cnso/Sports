package org.jash.sports.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.jash.common.mvvm.BaseViewModel
import org.jash.common.utils.log
import org.jash.common.utils.retrofit
import org.jash.sports.entry.User
import org.jash.sports.net.Service

class MineViewModel:BaseViewModel() {
    val userLiveData by lazy { MutableLiveData<User>() }
    val logoutLiveData by lazy { MutableLiveData<Boolean>() }
    val service by lazy { retrofit.create(Service::class.java) }
    fun getDetail() {
        viewModelScope.launch {
            try {
                val res = service.getUserDetail()
                if (res.code == 0) {
                    userLiveData.postValue(res.data)
                } else {
                    errorLiveData.postValue(res.msg)
                }
            } catch (e:Exception) {
                errorLiveData.postValue(e.message)
            }
        }
    }
    fun logout() {
        viewModelScope.launch {
            try {
                val res = service.logout()
                if (res.code == 0) {
                    logoutLiveData.postValue(true)
                } else {
                    errorLiveData.postValue(res.msg)
                }
            } catch (e:Exception) {
                errorLiveData.postValue(e.message)
            }
        }
    }
}