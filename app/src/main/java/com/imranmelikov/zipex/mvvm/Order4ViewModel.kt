package com.imranmelikov.zipex.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imranmelikov.zipex.model.Order3
import com.imranmelikov.zipex.model.Order4
import com.imranmelikov.zipex.repo.ZipexRepo
import com.imranmelikov.zipex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Order4ViewModel @Inject constructor(
    private val zipexRepo: ZipexRepo
):ViewModel() {
    private val order4Message= MutableLiveData<Resource<List<Order4>>>()
    val order4Msg: LiveData<Resource<List<Order4>>>
        get() = order4Message

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error: ${throwable.localizedMessage}")
        order4Message.value = Resource.error("Error", null)
    }

    fun insertOrder4(order4: Order4){
        viewModelScope.launch {
            zipexRepo.insertOrder4(order4)
        }
    }
    fun getOrder4(){
        order4Message.value= Resource.loading(null)
        viewModelScope.launch(exceptionHandler) {
            val response=zipexRepo.getOrder4s()
            order4Message.value= Resource.success(response)
        }
    }

    fun deleteOrder4(order4: Order4){
        viewModelScope.launch {
            zipexRepo.deleteOrder4(order4)
        }
    }
}