package com.imranmelikov.zipex.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imranmelikov.zipex.model.Order1
import com.imranmelikov.zipex.model.Order3
import com.imranmelikov.zipex.repo.ZipexRepo
import com.imranmelikov.zipex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Order3ViewModel @Inject constructor(
    private val zipexRepo: ZipexRepo
):ViewModel() {
    private val order3Message= MutableLiveData<Resource<List<Order3>>>()
    val order3Msg: LiveData<Resource<List<Order3>>>
        get() = order3Message

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error: ${throwable.localizedMessage}")
        order3Message.value = Resource.error("Error", null)
    }
    var order3:Order3=Order3("","",0,"","",0.0,"","","","","","","","","","")
    fun insertOrder3(order3: Order3){
        viewModelScope.launch {
            zipexRepo.insertOrder3(order3)
        }
    }
    fun getOrder3(){
        order3Message.value= Resource.loading(null)
        viewModelScope.launch(exceptionHandler) {
            val response=zipexRepo.getOrder3s()
            order3Message.value= Resource.success(response)
        }
    }

    fun deleteOrder3(order3: Order3){
        viewModelScope.launch {
            zipexRepo.deleteOrder3(order3)
        }
    }
    fun updateOrder3(order3: Order3){
        viewModelScope.launch {
            zipexRepo.updateOrder3(order3)
        }
    }
}