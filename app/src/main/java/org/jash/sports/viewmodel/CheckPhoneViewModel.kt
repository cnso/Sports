package org.jash.sports.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.jash.common.mvvm.BaseViewModel
import org.jash.common.utils.retrofit
import org.jash.sports.entry.Sign
import org.jash.sports.net.Service

class CheckPhoneViewModel:BaseViewModel() {
    val codeLiveData by lazy { MutableLiveData<Int>() }
    val signLiveData by lazy { MutableLiveData<Boolean>() }
    val service by lazy { retrofit.create(Service::class.java) }
    fun getCode(phone:String) {
        viewModelScope.launch {
            try {
                val res = service.getCode(phone)
                if (res.code == 0) {
                    codeLiveData.postValue(res.data)
                } else {
                    errorLiveData.postValue(res.msg)
                }
            } catch (e:Exception) {
                errorLiveData.postValue(e.message)
            }
        }
    }
    fun sign(sign:Sign) {
        viewModelScope.launch {
            try {
                val res = service.signPhone(sign)
                if (res.code == 0) {
                    signLiveData.postValue(true)
                } else {
                    errorLiveData.postValue(res.msg)
                }
            } catch (e:Exception) {
                errorLiveData.postValue(e.message)
            }
        }
    }
}