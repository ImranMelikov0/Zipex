package com.imranmelikov.zipex.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imranmelikov.zipex.model.Order1
import com.imranmelikov.zipex.model.Order2
import com.imranmelikov.zipex.repo.ZipexRepo
import com.imranmelikov.zipex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Order2ViewModel @Inject constructor(
    private val zipexRepo: ZipexRepo
):ViewModel() {
    private val order2Message= MutableLiveData<Resource<List<Order2>>>()
    val order2Msg: LiveData<Resource<List<Order2>>>
        get() = order2Message

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error: ${throwable.localizedMessage}")
        order2Message.value = Resource.error("Error", null)
    }
    var order2:Order2=Order2("","",0,"","",0.0,"","","","","","","","")
    fun insertOrder2(order2: Order2){
        viewModelScope.launch {
            zipexRepo.insertOrder2(order2)
        }
    }
    fun getOrder2(){
        order2Message.value= Resource.loading(null)
        viewModelScope.launch(exceptionHandler) {
            val response=zipexRepo.getOrder2s()
            order2Message.value= Resource.success(response)
        }
    }

    fun deleteOrder2(order2: Order2){
        viewModelScope.launch {
            zipexRepo.deleteOrder2(order2)
        }
    }
    fun updateOrder2(order2: Order2){
        viewModelScope.launch {
            zipexRepo.updateOrder2(order2)
        }
    }
}