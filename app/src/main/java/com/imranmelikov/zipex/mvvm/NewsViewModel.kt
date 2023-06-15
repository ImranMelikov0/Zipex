package com.imranmelikov.zipex.mvvm

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.imranmelikov.zipex.model.News
import com.imranmelikov.zipex.repo.ZipexRepo
import com.imranmelikov.zipex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
  private val zipexRepo: ZipexRepo
):ViewModel() {
    private var errorMessage=MutableLiveData<Resource<News>>()
    val errorMsg:LiveData<Resource<News>>
    get()=errorMessage

    private var newsMutableLivedata=MutableLiveData<News>()
    val newsLiveData:LiveData<News>
        get() = newsMutableLivedata

    private var newsMessage=MutableLiveData<Resource<List<News>>>()
    val newsMsg:LiveData<Resource<List<News>>>
        get()=newsMessage

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error: ${throwable.localizedMessage}")
        newsMessage.value = Resource.error("Error", null)
    }

    fun getNews(){
        newsMessage.value=Resource.loading(null)
        viewModelScope.launch(exceptionHandler) {
            var getNews=zipexRepo.getNews()
                newsMessage.value=Resource.success(getNews)
        }
    }
    fun makeNews(title:String,post:String,url:Uri?){
        if (title.isEmpty()||post.isEmpty()||url==null){
            errorMessage.value=Resource.error("Məlumatları daxil edin",null)
            return
        }else{
            val news=News(title,post,url.toString())
            insertNews(news)
            errorMessage.value=Resource.success(news)
            return
        }
    }

    fun resetNewsMessage(){
        newsMessage=MutableLiveData<Resource<List<News>>>()
    }
    fun resetErrorMessage(){
        errorMessage=MutableLiveData<Resource<News>>()
    }
   private fun insertNews(news: News){
        viewModelScope.launch {
            zipexRepo.insertNews(news)
        }
    }
    fun getNewsSingle(newsId:Int){
        viewModelScope.launch {
           val response= zipexRepo.getNew(newsId)
            newsMutableLivedata.value=response
        }
    }
}