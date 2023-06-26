package com.imranmelikov.zipex.repo

import androidx.lifecycle.LiveData
import com.imranmelikov.zipex.model.AdminLink
import com.imranmelikov.zipex.model.BalanceAzn
import com.imranmelikov.zipex.model.BalanceTotalAzn
import com.imranmelikov.zipex.model.BalanceTotalTry
import com.imranmelikov.zipex.model.BalanceTotalUsd
import com.imranmelikov.zipex.model.BalanceTry
import com.imranmelikov.zipex.model.BalanceUsd
import com.imranmelikov.zipex.model.Debt
import com.imranmelikov.zipex.model.DebtHistory
import com.imranmelikov.zipex.model.DebtTotal
import com.imranmelikov.zipex.model.Link
import com.imranmelikov.zipex.model.News
import com.imranmelikov.zipex.model.Notification
import com.imranmelikov.zipex.model.Order1
import com.imranmelikov.zipex.model.Order2
import com.imranmelikov.zipex.model.Order3
import com.imranmelikov.zipex.model.Order4
import com.imranmelikov.zipex.model.Order5
import com.imranmelikov.zipex.model.Order6
import com.imranmelikov.zipex.model.Order7
import com.imranmelikov.zipex.model.Order8
import com.imranmelikov.zipex.model.Order9
import com.imranmelikov.zipex.model.ZipexMoneyDepot
import com.imranmelikov.zipex.util.Resource

interface ZipexRepo {
    suspend fun insertNews(news: News)
   suspend fun getNews():List<News>
    suspend fun getNew(newsId:Int):News

    suspend fun insertNotification(notification: Notification)
    suspend fun updateNotification(notification: Notification)
   suspend fun getNotifications():List<Notification>
    suspend fun deleteNotification(notification: Notification)
    suspend fun getNotification(NotificationId:Int): Notification

    suspend fun insertLink(link: Link)
   suspend fun getLinks():List<Link>
    suspend fun deleteLink(link: Link)
    suspend fun updateLink(link: Link)

    suspend fun insertAdminLink(adminLink: AdminLink)
   suspend fun getAdminLinks():List<AdminLink>
    suspend fun deleteAdminLink(adminLink: AdminLink)
    suspend fun updateAdminLink(adminLink: AdminLink)

    suspend fun insertOrder1(order1: Order1)
   suspend fun getOrder1s():List<Order1>
    suspend fun deleteOrder1(order1: Order1)
    suspend fun updateOrder1(order1: Order1)

    suspend fun insertOrder2(order2: Order2)
   suspend fun getOrder2s():List<Order2>
    suspend fun deleteOrder2(order2: Order2)
    suspend fun updateOrder2(order2: Order2)

    suspend fun insertOrder3(order3: Order3)
   suspend fun getOrder3s():List<Order3>
    suspend fun deleteOrder3(order3: Order3)
    suspend fun updateOrder3(order3: Order3)

    suspend fun insertOrder4(order4: Order4)
   suspend fun getOrder4s():List<Order4>
    suspend fun deleteOrder4(order4: Order4)

    suspend fun insertOrder5(order5: Order5)
   suspend fun getOrder5s():List<Order5>
    suspend fun deleteOrder5(order5: Order5)

    suspend fun insertOrder6(order6: Order6)
   suspend fun getOrder6s():List<Order6>
    suspend fun deleteOrder6(order6: Order6)

    suspend fun insertOrder7(order7: Order7)
   suspend fun getOrder7s():List<Order7>
    suspend fun deleteOrder7(order7: Order7)

    suspend fun insertOrder8(order8: Order8)
   suspend fun getOrder8s():List<Order8>
    suspend fun deleteOrder8(order8: Order8)

 suspend fun insertOrder9(order9: Order9)
 suspend fun getOrder9s():List<Order9>

    suspend fun insertBalanceTry(balanceTry: BalanceTry)
   suspend fun getBalanceTry():List<BalanceTry>

    suspend fun insertBalanceAzn(balanceAzn: BalanceAzn)
   suspend fun getBalanceAzn():List<BalanceAzn>

    suspend fun insertBalanceTotalTry(balanceTotalTry: BalanceTotalTry)
    suspend fun updateBalanceTotalTry(balanceTotalTry: BalanceTotalTry)
   suspend fun getBalanceTotalTry():BalanceTotalTry?

    suspend fun insertBalanceTotalAzn(balanceTotalAzn: BalanceTotalAzn)
    suspend fun updateBalanceTotalAzn(balanceTotalAzn: BalanceTotalAzn)
   suspend fun getBalanceTotalAzn():BalanceTotalAzn?

 suspend fun insertBalanceUsd(balanceUsd: BalanceUsd)
 suspend fun getBalanceUsd():List<BalanceUsd>

 suspend fun insertBalanceTotalUsd(balanceTotalUsd: BalanceTotalUsd)
 suspend fun updateBalanceTotalUsd(balanceTotalUsd: BalanceTotalUsd)
 suspend fun getBalanceTotalUsd():BalanceTotalUsd?

    suspend fun insertDebt(debt: Debt)
    suspend fun deleteDebt(debt: Debt)
   suspend fun getDebt():List<Debt>
    suspend fun deleteDebts()

    suspend fun insertDebtHistory(debtHistory: DebtHistory)
   suspend fun getDebtHistory():List<DebtHistory>

    suspend fun insertDebtTotal(debtTotal: DebtTotal)
    suspend fun updateDebtTotal(debtTotal: DebtTotal)
   suspend fun getDebtTotal():DebtTotal?

    suspend fun insertZipexMoney(zipexMoneyDepot: ZipexMoneyDepot)
   suspend fun getZipexMoney():List<ZipexMoneyDepot>
    suspend fun updateZipexMoney(zipexMoneyDepot: ZipexMoneyDepot)


}