package com.imranmelikov.zipex.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.imranmelikov.zipex.model.Link
import com.imranmelikov.zipex.repo.ZipexRepo
import com.imranmelikov.zipex.util.Resource
import com.imranmelikov.zipex.view.LinkFragmentDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.math.RoundingMode
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
class LinkViewModel @Inject constructor(
    private val zipexRepo: ZipexRepo
):ViewModel() {

    private var linkMessage=MutableLiveData<Resource<Link>>()
    val linkMsg:LiveData<Resource<Link>>
        get() = linkMessage

    fun makeLink(url:String,category:String,count:Int?,color:String,size:String,price:Double?,comment:String,history:String,country:String,sigorta:String,payment:String){
        if (url.isEmpty()||category.isEmpty()||count==null||color.isEmpty()||size.isEmpty()||price==null||history.isEmpty()||country.isEmpty()||sigorta.isEmpty()||payment.isEmpty()||price==0.0){
            linkMessage.value=Resource.error("Məlumatları daxil edin",null)
        }else{
            if (country.equals("Türkiye")){
                val price1= price.toDouble()
                val decimalFormat = DecimalFormat("#.##")
                decimalFormat.roundingMode = RoundingMode.HALF_UP
                val roundedAmount = decimalFormat.format(price1).toDouble()
                val link=Link(url,category,count,color,size,roundedAmount,comment,history,country,sigorta,payment)
                insertLink(link)
                linkMessage.value=Resource.success(link)
            }else if(country.equals("Amerika")){
                val price1= price.toDouble()
                val decimalFormat = DecimalFormat("#.##")
                decimalFormat.roundingMode = RoundingMode.HALF_UP
                val roundedAmount = decimalFormat.format(price1).toDouble()
                val link=Link(url,category,count,color,size,roundedAmount,comment,history,country,sigorta,payment)
                insertLink(link)
                linkMessage.value=Resource.success(link)
            }else{
                linkMessage.value=Resource.error("Ölkə hissəsinə Türkiye və ya Amerika daxil edin",null)
            }

        }
    }

  fun resetLinkMessage(){
      linkMessage=MutableLiveData<Resource<Link>>()
  }
    private fun insertLink(link: Link){
        viewModelScope.launch {
            zipexRepo.insertLink(link)
        }
    }
}