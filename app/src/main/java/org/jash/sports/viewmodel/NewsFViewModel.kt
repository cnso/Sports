package org.jash.sports.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.jash.common.mvvm.BaseViewModel
import org.jash.common.utils.retrofit
import org.jash.sports.dao.CategoryDao
import org.jash.sports.dao.NewsDatabase
import org.jash.sports.entry.Category
import org.jash.sports.net.Service

class NewsFViewModel(val dao: CategoryDao):BaseViewModel() {
    val categoryLiveData by lazy { MutableLiveData<List<Category>>() }
    val service by lazy { retrofit.create(Service::class.java) }
    fun loadCategory() {
        viewModelScope.launch {
            try {
                val res = service.getAllCategory()
                if (res.code == 0) {
                    dao.insert(*res.data.toTypedArray())
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