package com.imranmelikov.zipex.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imranmelikov.zipex.model.Order3
import com.imranmelikov.zipex.model.Order4
import com.imranmelikov.zipex.model.Order8
import com.imranmelikov.zipex.repo.ZipexRepo
import com.imranmelikov.zipex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Order8ViewModel @Inject constructor(
    private val zipexRepo: ZipexRepo
):ViewModel() {
    private val order8Message= MutableLiveData<Resource<List<Order8>>>()
    val order8Msg: LiveData<Resource<List<Order8>>>
        get() = order8Message

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error: ${throwable.localizedMessage}")
        order8Message.value = Resource.error("Error", null)
    }

    fun insertOrder8(order8: Order8){
        viewModelScope.launch {
            zipexRepo.insertOrder8(order8)
        }
    }
    fun getOrder8(){
        order8Message.value= Resource.loading(null)
        viewModelScope.launch(exceptionHandler) {
            val response=zipexRepo.getOrder8s()
            order8Message.value= Resource.success(response)
        }
    }

    fun deleteOrder8(order8: Order8){
        viewModelScope.launch {
            zipexRepo.deleteOrder8(order8)
        }
    }
}