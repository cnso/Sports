package org.jash.sports.viewmodel

import androidx.lifecycle.MutableLiveData
import org.jash.common.mvvm.BaseViewModel
import org.jash.common.utils.log
import org.jash.sports.entry.User

class LoginViewModel:BaseViewModel() {
    val tokenLiveData by lazy { MutableLiveData<String>() }
    fun login(user:User) {
        log(user)
    }
}