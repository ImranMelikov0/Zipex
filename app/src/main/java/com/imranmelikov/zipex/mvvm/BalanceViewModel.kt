package com.imranmelikov.zipex.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imranmelikov.zipex.model.BalanceAzn
import com.imranmelikov.zipex.model.BalanceTotalAzn
import com.imranmelikov.zipex.model.BalanceTotalTry
import com.imranmelikov.zipex.model.BalanceTry
import com.imranmelikov.zipex.model.Debt
import com.imranmelikov.zipex.model.Link
import com.imranmelikov.zipex.repo.ZipexRepo
import com.imranmelikov.zipex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BalanceViewModel @Inject constructor(
    private val zipexRepo: ZipexRepo
):ViewModel() {

    private val balanceTotalTryMutableLiveData=MutableLiveData<Resource<BalanceTotalTry>>()
    val balanceTotalTryLiveData:LiveData<Resource<BalanceTotalTry>>
        get() = balanceTotalTryMutableLiveData

    private val balanceTryMutableLiveData=MutableLiveData<Resource<List<BalanceTry>>>()
    val balanceTryLiveData:LiveData<Resource<List<BalanceTry>>>
        get() = balanceTryMutableLiveData

    private val updateBalanceTotalTry=MutableLiveData<Resource<BalanceTotalTry>>()
    val updateBalanceTryLiveData:LiveData<Resource<BalanceTotalTry>>
        get() = updateBalanceTotalTry


    private val balanceTotalAznMutableLiveData=MutableLiveData<Resource<BalanceTotalAzn>>()
    val balanceTotalAznLiveData:LiveData<Resource<BalanceTotalAzn>>
        get() = balanceTotalAznMutableLiveData

    private val balanceAznMutableLiveData=MutableLiveData<Resource<List<BalanceAzn>>>()
    val balanceAznLiveData:LiveData<Resource<List<BalanceAzn>>>
        get() = balanceAznMutableLiveData

    private val updateBalanceTotalAzn=MutableLiveData<Resource<BalanceTotalAzn>>()
    val updateBalanceAznLiveData:LiveData<Resource<BalanceTotalAzn>>
        get() = updateBalanceTotalAzn

    private val exceptionHandlerTotalAzn = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error: ${throwable.localizedMessage}")
        balanceTotalAznMutableLiveData.value = Resource.error("Error", null)
    }
    private val exceptionHandlerAzn = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error: ${throwable.localizedMessage}")
        balanceAznMutableLiveData.value = Resource.error("Error", null)
    }

    private val exceptionHandlerTotalTry = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error: ${throwable.localizedMessage}")
        balanceTotalTryMutableLiveData.value = Resource.error("Error", null)
    }
    private val exceptionHandlerTry = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error: ${throwable.localizedMessage}")
        balanceTryMutableLiveData.value = Resource.error("Error", null)
    }
    private val exceptionHandlerUpdateTry = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error: ${throwable.localizedMessage}")
        updateBalanceTotalTry.value = Resource.error("Error", null)
    }
    private val exceptionHandleUpdateAzn = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error: ${throwable.localizedMessage}")
       updateBalanceTotalAzn.value = Resource.error("Error", null)
    }

    var showFirst:Boolean=true
    var showFirstDebt:Int=0
    var getDouble:Double=0.0
    var onItemClick="a"
    var getBalanceTotalAzn:BalanceTotalAzn=BalanceTotalAzn(0.0)
    var getBalanceTotalTry:BalanceTotalTry=BalanceTotalTry(0.0)
    var getDebt:Debt=Debt("",0.0)
    var getLink:Link=Link("","",0,"","",0.0,"","","","","")
    fun getBalanceTry(){
        balanceTryMutableLiveData.value=Resource.loading(null)
        viewModelScope.launch(exceptionHandlerTry){
            val response=zipexRepo.getBalanceTry()
            balanceTryMutableLiveData.value=Resource.success(response)
        }
    }
    fun getTotalBalanceTry(){
        balanceTotalTryMutableLiveData.value=Resource.loading(null)
        viewModelScope.launch(exceptionHandlerTotalTry){
            val response=zipexRepo.getBalanceTotalTry()
            balanceTotalTryMutableLiveData.value=Resource.success(response)
        }
    }
    fun insertTotalBalanceTry(totalTry: BalanceTotalTry){
        viewModelScope.launch {
            zipexRepo.insertBalanceTotalTry(totalTry)
        }
    }
    fun insertBalanceTry(balanceTry: BalanceTry){
        viewModelScope.launch {
            zipexRepo.insertBalanceTry(balanceTry)
        }
    }
    fun updateBalanceTotalTry(balanceTotalTry: BalanceTotalTry){
        viewModelScope.launch (exceptionHandlerUpdateTry){
            zipexRepo.updateBalanceTotalTry(balanceTotalTry)
            updateBalanceTotalTry.value=Resource.success(balanceTotalTry)
        }
    }

    fun getBalanceAzn(){
        balanceAznMutableLiveData.value=Resource.loading(null)
        viewModelScope.launch(exceptionHandlerAzn){
            val response=zipexRepo.getBalanceAzn()
            balanceAznMutableLiveData.value=Resource.success(response)
        }
    }
    fun getTotalBalanceAzn(){
        balanceTotalAznMutableLiveData.value=Resource.loading(null)
        viewModelScope.launch(exceptionHandlerTotalAzn){
            val response=zipexRepo.getBalanceTotalAzn()
            balanceTotalAznMutableLiveData.value=Resource.success(response)
        }
    }
    fun insertTotalBalanceAzn(totalAzn: BalanceTotalAzn){
        viewModelScope.launch {
            zipexRepo.insertBalanceTotalAzn(totalAzn)
        }
    }
    fun insertBalanceAzn(balanceAzn: BalanceAzn){
        viewModelScope.launch {
            zipexRepo.insertBalanceAzn(balanceAzn)
        }
    }
    fun updateBalanceTotalAzn(balanceTotalAzn: BalanceTotalAzn){
        viewModelScope.launch (exceptionHandleUpdateAzn){
            zipexRepo.updateBalanceTotalAzn(balanceTotalAzn)
            updateBalanceTotalAzn.value=Resource.success(balanceTotalAzn)
        }
    }
    }