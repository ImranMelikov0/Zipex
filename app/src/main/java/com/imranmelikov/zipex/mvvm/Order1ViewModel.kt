package com.imranmelikov.zipex.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imranmelikov.zipex.model.Order1
import com.imranmelikov.zipex.repo.ZipexRepo
import com.imranmelikov.zipex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Order1ViewModel @Inject constructor(
    private val zipexRepo: ZipexRepo
):ViewModel() {

    private val order1Message=MutableLiveData<Resource<List<Order1>>>()
    val order1Msg:LiveData<Resource<List<Order1>>>
        get() = order1Message

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error: ${throwable.localizedMessage}")
        order1Message.value = Resource.error("Error", null)
    }

    var onItemString="a"
    var order1:Order1=Order1("","",0,"","",0.0,"","","","","","","","")
    fun getOrder1(){
        order1Message.value=Resource.loading(null)
        viewModelScope.launch(exceptionHandler) {
            val response=zipexRepo.getOrder1s()
            order1Message.value=Resource.success(response)
        }
    }

    fun deleteOrder1(order1: Order1){
        viewModelScope.launch {
            zipexRepo.deleteOrder1(order1)
        }
    }
    fun updateOrder1(order1: Order1){
        viewModelScope.launch {
            zipexRepo.updateOrder1(order1)
        }
    }
}