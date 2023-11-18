package org.jash.sports.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.jash.common.mvvm.BaseViewModel
import org.jash.common.utils.retrofit
import org.jash.sports.entry.Sign
import org.jash.sports.entry.User
import org.jash.sports.net.Service

class RegistryViewModel:BaseViewModel() {
    val registryLiveData by lazy{ MutableLiveData<Boolean>() }
    val service by lazy { retrofit.create(Service::class.java) }
    fun registry(user: User) {
        viewModelScope.launch {
            try {
                val res = service.registry(user)
                if (res.code == 0) {
                    registryLiveData.postValue(true)
                } else {
                    errorLiveData.postValue(res.msg)
                }
            } catch (e:Exception) {
                errorLiveData.postValue(e.message)
            }
        }
    }
}