package com.imranmelikov.zipex.mvvm

import androidx.lifecycle.ViewModel
import com.imranmelikov.zipex.repo.ZipexRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val zipexRepo: ZipexRepo
):ViewModel() {

}