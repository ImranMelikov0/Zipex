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
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val zipexRepo: ZipexRepo
):ViewModel() {

    private val cartMessage=MutableLiveData<Resource<List<Link>>>()
    val cartMsg:LiveData<Resource<List<Link>>>
        get() = cartMessage

    private val updateCart=MutableLiveData<Resource<Link>>()
    val updateLink:LiveData<Resource<Link>>
        get() = updateCart

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error: ${throwable.localizedMessage}")
        cartMessage.value = Resource.error("Error", null)
    }
    fun refreshData(startDate:String,endDate:String,link: Link){

        val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm:ss")

        val startDate1 = dateFormat.parse(startDate)
        val endDate1 = dateFormat.parse(endDate)

        val diffInMillis = endDate1.time - startDate1.time

        val diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillis)
        val diffInHours = TimeUnit.MILLISECONDS.toHours(diffInMillis)
        val diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillis)
        println(diffInMinutes)
        if (diffInDays.toInt()>=1 && link.payment!="Ödənilib"){
            deleteCart(link)
        }else{

        }

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
}