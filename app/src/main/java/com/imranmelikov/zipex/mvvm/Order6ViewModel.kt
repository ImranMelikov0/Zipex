package com.imranmelikov.zipex.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imranmelikov.zipex.model.Order3
import com.imranmelikov.zipex.model.Order4
import com.imranmelikov.zipex.model.Order6
import com.imranmelikov.zipex.repo.ZipexRepo
import com.imranmelikov.zipex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Order6ViewModel @Inject constructor(
    private val zipexRepo: ZipexRepo
):ViewModel() {
    private val order6Message= MutableLiveData<Resource<List<Order6>>>()
    val order6Msg: LiveData<Resource<List<Order6>>>
        get() = order6Message

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error: ${throwable.localizedMessage}")
        order6Message.value = Resource.error("Error", null)
    }

    fun insertOrder6(order6: Order6){
        viewModelScope.launch {
            zipexRepo.insertOrder6(order6)
        }
    }
    fun getOrder6(){
        order6Message.value= Resource.loading(null)
        viewModelScope.launch(exceptionHandler) {
            val response=zipexRepo.getOrder6s()
            order6Message.value= Resource.success(response)
        }
    }

    fun deleteOrder6(order6: Order6){
        viewModelScope.launch {
            zipexRepo.deleteOrder6(order6)
        }
    }
}