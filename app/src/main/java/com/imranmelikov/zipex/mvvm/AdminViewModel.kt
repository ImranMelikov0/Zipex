package com.imranmelikov.zipex.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imranmelikov.zipex.model.AdminLink
import com.imranmelikov.zipex.model.Order1
import com.imranmelikov.zipex.repo.ZipexRepo
import com.imranmelikov.zipex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminViewModel @Inject constructor(
    private val zipexRepo: ZipexRepo
):ViewModel() {

    private val adminLinkMessage=MutableLiveData<Resource<List<AdminLink>>>()
    val adminLinkMsg:LiveData<Resource<List<AdminLink>>>
        get() = adminLinkMessage
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error: ${throwable.localizedMessage}")
        adminLinkMessage.value = Resource.error("Error", null)
    }

    fun insertOrder1(order1: Order1){
        viewModelScope.launch {
            zipexRepo.insertOrder1(order1)
        }
    }
    fun updateAdminLink(adminLink: AdminLink){
        viewModelScope.launch {
            zipexRepo.updateAdminLink(adminLink)
        }
    }

    fun getAdminLink(){
        adminLinkMessage.value=Resource.loading(null)
        viewModelScope.launch(exceptionHandler) {
          val response= zipexRepo.getAdminLinks()
            adminLinkMessage.value=Resource.success(response)
        }
    }

    fun deleteAdminLink(adminLink: AdminLink){
        viewModelScope.launch{
            zipexRepo.deleteAdminLink(adminLink)
        }
    }
    fun insertAdminLink(adminLink: AdminLink){
        viewModelScope.launch {
            zipexRepo.insertAdminLink(adminLink)
        }
    }
}