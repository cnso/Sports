package org.jash.sports.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.jash.common.mvvm.BaseViewModel
import org.jash.common.utils.retrofit
import org.jash.sports.dao.CategoryDao
import org.jash.sports.entry.Category
import org.jash.sports.entry.News
import org.jash.sports.entry.Page
import org.jash.sports.net.Service

class CategoryViewModel(val dao: CategoryDao):BaseViewModel() {
//    val categoryLiveData by lazy { MutableLiveData<Category>() }
//    val allCategoryLiveData by lazy { MutableLiveData<List<Category>>() }
    val pageLiveData by lazy { MutableLiveData<Page<News>>() }
    val service by lazy { retrofit.create(Service::class.java) }
//    fun loadCategory(id:Int){
//        viewModelScope.launch {
//            val category = dao.findById(id)
//            categoryLiveData.postValue(category)
//        }
//
//    }
//    fun loadAllCategory() {
//        viewModelScope.launch {
//            try {
//                val res = service.getAllCategory()
//                if (res.code == 0) {
//                    allCategoryLiveData.postValue(res.data)
//                } else {
//                    errorLiveData.postValue(res.msg)
//                }
//            } catch (e:Exception) {
//                errorLiveData.postValue(e.message)
//            }
//        }
//    }
    fun loadPage(type:Int, page:Int, size:Int) {
        viewModelScope.launch {
            try {
                val res = service.getNewsByPage(type, page, size)
                if (res.code == 0) {
                    pageLiveData.postValue(res.data)
                } else {
                    errorLiveData.postValue(res.msg)
                }
            } catch (e:Exception) {
                errorLiveData.postValue(e.message)
            }
        }
    }
}