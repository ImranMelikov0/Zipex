package com.imranmelikov.zipex.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imranmelikov.zipex.model.Order3
import com.imranmelikov.zipex.model.Order4
import com.imranmelikov.zipex.model.Order7
import com.imranmelikov.zipex.repo.ZipexRepo
import com.imranmelikov.zipex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Order7ViewModel @Inject constructor(
    private val zipexRepo: ZipexRepo
):ViewModel() {
    private val order7Message= MutableLiveData<Resource<List<Order7>>>()
    val order7Msg: LiveData<Resource<List<Order7>>>
        get() = order7Message

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error: ${throwable.localizedMessage}")
        order7Message.value = Resource.error("Error", null)
    }

    fun insertOrder7(order7: Order7){
        viewModelScope.launch {
            zipexRepo.insertOrder7(order7)
        }
    }
    fun getOrder7(){
        order7Message.value= Resource.loading(null)
        viewModelScope.launch(exceptionHandler) {
            val response=zipexRepo.getOrder7s()
            order7Message.value= Resource.success(response)
        }
    }

    fun deleteOrder7(order7: Order7){
        viewModelScope.launch {
            zipexRepo.deleteOrder7(order7)
        }
    }
}