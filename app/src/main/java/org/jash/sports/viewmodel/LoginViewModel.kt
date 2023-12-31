package org.jash.sports.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.jash.common.mvvm.BaseViewModel
import org.jash.common.utils.log
import org.jash.common.utils.retrofit
import org.jash.sports.entry.User
import org.jash.sports.net.Service

class LoginViewModel:BaseViewModel() {
    val tokenLiveData by lazy { MutableLiveData<String>() }
    val service by lazy { retrofit.create(Service::class.java) }
    fun login(user:User) {
        viewModelScope.launch {
            try {
                val res = service.login(mapOf("name" to user.username, "password" to user.password))
                if (res.code == 0) {
                    tokenLiveData.postValue(res.data)
                } else {
                    errorLiveData.postValue(res.msg)
                }
            } catch (e:Exception) {
                errorLiveData.postValue(e.message)
            }
        }
    }
}