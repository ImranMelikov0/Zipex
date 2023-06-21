package com.imranmelikov.zipex.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imranmelikov.zipex.model.Link
import com.imranmelikov.zipex.repo.ZipexRepo
import com.imranmelikov.zipex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val zipexRepo: ZipexRepo
):ViewModel() {

    var adminBoolean:Boolean=true

    private val cartMessage=MutableLiveData<Resource<List<Link>>>()
    val cartMsg:LiveData<Resource<List<Link>>>
        get() = cartMessage

    private val cartId=MutableLiveData<Link>()
    val cartid:LiveData<Link>
        get() = cartId

    private val updateCart=MutableLiveData<Resource<Link>>()
    val updateLink:LiveData<Resource<Link>>
        get() = updateCart

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error: ${throwable.localizedMessage}")
        cartMessage.value = Resource.error("Error", null)
    }
    fun getCarts(){
        cartMessage.value=Resource.loading(null)
        viewModelScope.launch(exceptionHandler) {
            val response=zipexRepo.getLinks()
            cartMessage.value=Resource.success(response)
        }
    }
    fun deleteCart(link: Link){
        viewModelScope.launch {
            zipexRepo.deleteLink(link)
        }
    }
    fun updateCart(link: Link){
        updateCart.value=Resource.loading(null)
        viewModelScope.launch {
            zipexRepo.updateLink(link)
            updateCart.value=Resource.success(link)
        }
    }
    fun getCart(linkId:Int){
        viewModelScope.launch {
           val link= zipexRepo.getLink(linkId)
            cartId.value=link
        }
    }
}