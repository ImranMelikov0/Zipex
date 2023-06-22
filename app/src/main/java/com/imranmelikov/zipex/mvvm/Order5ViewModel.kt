package com.imranmelikov.zipex.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imranmelikov.zipex.model.Order3
import com.imranmelikov.zipex.model.Order4
import com.imranmelikov.zipex.model.Order5
import com.imranmelikov.zipex.repo.ZipexRepo
import com.imranmelikov.zipex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Order5ViewModel @Inject constructor(
    private val zipexRepo: ZipexRepo
):ViewModel() {
    private val order5Message= MutableLiveData<Resource<List<Order5>>>()
    val order5Msg: LiveData<Resource<List<Order5>>>
        get() = order5Message

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error: ${throwable.localizedMessage}")
        order5Message.value = Resource.error("Error", null)
    }

    fun insertOrder5(order5: Order5){
        viewModelScope.launch {
            zipexRepo.insertOrder5(order5)
        }
    }
    fun getOrder5(){
        order5Message.value= Resource.loading(null)
        viewModelScope.launch(exceptionHandler) {
            val response=zipexRepo.getOrder5s()
            order5Message.value= Resource.success(response)
        }
    }

    fun deleteOrder5(order5: Order5){
        viewModelScope.launch {
            zipexRepo.deleteOrder5(order5)
        }
    }
}