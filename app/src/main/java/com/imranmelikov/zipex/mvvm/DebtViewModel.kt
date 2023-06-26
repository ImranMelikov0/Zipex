package com.imranmelikov.zipex.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imranmelikov.zipex.model.BalanceTotalAzn
import com.imranmelikov.zipex.model.BalanceTotalTry
import com.imranmelikov.zipex.model.Debt
import com.imranmelikov.zipex.model.DebtHistory
import com.imranmelikov.zipex.model.DebtTotal
import com.imranmelikov.zipex.repo.ZipexRepo
import com.imranmelikov.zipex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DebtViewModel @Inject constructor(
    private val zipexRepo: ZipexRepo
):ViewModel() {

    private val debtMessage=MutableLiveData<Resource<List<Debt>>>()
    val debtMsg:LiveData<Resource<List<Debt>>>
        get() = debtMessage

    var getDebtTotal:DebtTotal=DebtTotal(0.0)


    private val debtTotalMessage=MutableLiveData<Resource<DebtTotal>>()
    val debtTotalMsg:LiveData<Resource<DebtTotal>>
        get() = debtTotalMessage

    private val debtHistoryMessage=MutableLiveData<Resource<List<DebtHistory>>>()
    val debtHistoryMsg:LiveData<Resource<List<DebtHistory>>>
        get() = debtHistoryMessage

    private val exceptionHandlerDebtTotal = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error: ${throwable.localizedMessage}")
        debtTotalMessage.value = Resource.error("Error", null)
    }
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error: ${throwable.localizedMessage}")
        debtMessage.value = Resource.error("Error", null)
    }
    private val exceptionHandlerHistory = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error: ${throwable.localizedMessage}")
        debtHistoryMessage.value = Resource.error("Error", null)
    }
    fun insertDebt(debt: Debt){
        viewModelScope.launch {
            zipexRepo.insertDebt(debt)
        }
    }
    fun getDebt(){
        debtMessage.value=Resource.loading(null)
        viewModelScope.launch(exceptionHandler) {
           val response= zipexRepo.getDebt()
            debtMessage.value=Resource.success(response)
        }
    }

    fun deleteDebtAll(){
        viewModelScope.launch {
            zipexRepo.deleteDebts()
        }
    }
    fun deleteDebt(debt: Debt){
        viewModelScope.launch {
            zipexRepo.deleteDebt(debt)
        }
    }
    fun getInsertTotalDebt() {
        viewModelScope.launch {
            val response = zipexRepo.getDebtTotal()
            if (response == null) {
                val totalDebt = 0.0
                val debtTotal = DebtTotal(totalDebt)
                insertDebtTotal(debtTotal)
            } else {
                println("success")
            }
        }
    }
    fun insertDebtTotal(debtTotal: DebtTotal){
        viewModelScope.launch {
            zipexRepo.insertDebtTotal(debtTotal)
        }
    }
    fun updateDebtTotal(debtTotal: DebtTotal){
        viewModelScope.launch {
            zipexRepo.updateDebtTotal(debtTotal)
        }
    }
    fun getDebtTotal(){
        debtTotalMessage.value=Resource.loading(null)
        viewModelScope.launch(exceptionHandlerDebtTotal) {
            val response= zipexRepo.getDebtTotal()
            debtTotalMessage.value=Resource.success(response)
        }
    }
    fun insertDebtHistory(debtHistory: DebtHistory){
        viewModelScope.launch {
            zipexRepo.insertDebtHistory(debtHistory)
        }
    }
    fun getDebtHistory(){
        debtHistoryMessage.value=Resource.loading(null)
        viewModelScope.launch(exceptionHandlerHistory) {
           val response= zipexRepo.getDebtHistory()
            debtHistoryMessage.value=Resource.success(response)
        }
    }
}