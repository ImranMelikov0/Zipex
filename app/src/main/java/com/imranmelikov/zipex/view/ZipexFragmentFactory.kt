package com.imranmelikov.zipex.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.imranmelikov.zipex.adapter.NotificationAdapter
import com.imranmelikov.zipex.adapter.RegularOrderAdapter
import com.imranmelikov.zipex.adapter.newsAdapter
import javax.inject.Inject

class ZipexFragmentFactory @Inject constructor(
   private val newsAdapter: newsAdapter,
   private val glide:RequestManager,
   private val notificationAdapter: NotificationAdapter,
   private val regularOrderAdapter: RegularOrderAdapter
):FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when(className){
            HomeFragment::class.java.name->HomeFragment(newsAdapter)
            NewsDetailFragment::class.java.name->NewsDetailFragment(glide)
            NotificationFragment::class.java.name->NotificationFragment(notificationAdapter)
            RegularOrderFragment::class.java.name->RegularOrderFragment(regularOrderAdapter)
            else->super.instantiate(classLoader, className)
        }

    }
}