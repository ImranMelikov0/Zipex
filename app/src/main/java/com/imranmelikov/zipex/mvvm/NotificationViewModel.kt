package com.imranmelikov.zipex.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imranmelikov.zipex.model.News
import com.imranmelikov.zipex.model.Notification
import com.imranmelikov.zipex.repo.ZipexRepo
import com.imranmelikov.zipex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val zipexRepo: ZipexRepo
):ViewModel() {

    private val notificationsMessage=MutableLiveData<Resource<List<Notification>>>()
    val notificationMsg:LiveData<Resource<List<Notification>>>
        get() = notificationsMessage

    private var notificationErrorMessage=MutableLiveData<Resource<Notification>>()
    val notificationErrorMsg:LiveData<Resource<Notification>>
        get() = notificationErrorMessage

    private val notificationSingle=MutableLiveData<Notification>()
    val notificationSnl:LiveData<Notification>
        get() = notificationSingle

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error: ${throwable.localizedMessage}")
        notificationsMessage.value = Resource.error("Error", null)
    }
    fun getNotifications(){
        notificationsMessage.value=Resource.loading(null)
        viewModelScope.launch(exceptionHandler) {
            val response= zipexRepo.getNotifications()
            notificationsMessage.value=Resource.success(response)
        }
    }

    fun makeNotification(title:String,post:String){
        if (title.isEmpty()||post.isEmpty()){
            notificationErrorMessage.value=Resource.error("Məlumatları daxil edin",null)
        }else{
            val notification=Notification(title,post)
            insertNotification(notification)
            notificationErrorMessage.value=Resource.success(notification)
        }
    }

    fun resetNotificationErrorMessage(){
        notificationErrorMessage=MutableLiveData<Resource<Notification>>()
    }
   private fun insertNotification(notification: Notification){
        viewModelScope.launch {
            zipexRepo.insertNotification(notification)
        }
    }
    fun deleteNotification(notification: Notification){
        viewModelScope.launch{
            zipexRepo.deleteNotification(notification)
        }
    }
    fun getNotificationSingle(notificationId:Int){
        viewModelScope.launch{
          val response=  zipexRepo.getNotification(notificationId)
            notificationSingle.value=response
        }
    }
}