package com.imranmelikov.zipex.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.imranmelikov.zipex.adapter.AdminAdapter
import com.imranmelikov.zipex.adapter.AdminDetailAdapter
import com.imranmelikov.zipex.adapter.BalanceAdapter
import com.imranmelikov.zipex.adapter.CartAdapter
import com.imranmelikov.zipex.adapter.DebtAdapter
import com.imranmelikov.zipex.adapter.DebtHistoryAdapter
import com.imranmelikov.zipex.adapter.NotificationAdapter
import com.imranmelikov.zipex.adapter.OrderAdapter
import com.imranmelikov.zipex.adapter.RegularOrderAdapter
import com.imranmelikov.zipex.adapter.newsAdapter
import com.imranmelikov.zipex.model.DebtHistory
import javax.inject.Inject

class ZipexFragmentFactory @Inject constructor(
   private val newsAdapter: newsAdapter,
   private val glide:RequestManager,
   private val notificationAdapter: NotificationAdapter,
   private val regularOrderAdapter: RegularOrderAdapter,
   private val cartAdapter: CartAdapter,
   private val balanceAdapter: BalanceAdapter,
   private val adminAdapter: AdminAdapter,
   private val adminDetailAdapter: AdminDetailAdapter,
   private val orderAdapter: OrderAdapter,
   private val debtAdapter: DebtAdapter,
   private val debtHistoryAdapter: DebtHistoryAdapter
):FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when(className){
            HomeFragment::class.java.name->HomeFragment(newsAdapter)
            NewsDetailFragment::class.java.name->NewsDetailFragment(glide)
            NotificationFragment::class.java.name->NotificationFragment(notificationAdapter)
            RegularOrderFragment::class.java.name->RegularOrderFragment(regularOrderAdapter)
            CartFragment::class.java.name->CartFragment(cartAdapter)
            BalanceFragment::class.java.name->BalanceFragment(balanceAdapter)
            AdminFragment::class.java.name->AdminFragment(adminAdapter)
            AdminDetailFragment::class.java.name->AdminDetailFragment(adminDetailAdapter)
            OrderFragment::class.java.name->OrderFragment(orderAdapter)
            DebtFragment::class.java.name->DebtFragment(debtAdapter)
            DebtHistoryFragment::class.java.name->DebtHistoryFragment(debtHistoryAdapter)
            else->super.instantiate(classLoader, className)
        }

    }
}