package com.imranmelikov.zipex.repo

import androidx.lifecycle.LiveData
import com.imranmelikov.zipex.model.AdminLink
import com.imranmelikov.zipex.model.BalanceAzn
import com.imranmelikov.zipex.model.BalanceTotalAzn
import com.imranmelikov.zipex.model.BalanceTotalTry
import com.imranmelikov.zipex.model.BalanceTry
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
import com.imranmelikov.zipex.model.ZipexMoneyDepot
import com.imranmelikov.zipex.util.Resource

interface ZipexRepo {
    suspend fun insertNews(news: News)
   suspend fun getNews():List<News>
    suspend fun getNew(newsId:Int):News

    suspend fun insertNotification(notification: Notification)
   suspend fun getNotifications():List<Notification>
    suspend fun deleteNotification(notification: Notification)
    suspend fun getNotification(NotificationId:Int): Notification

    suspend fun insertLink(link: Link)
   suspend fun getLinks():List<Link>
    suspend fun deleteLink(link: Link)
    suspend fun updateLink(link: Link)
    suspend fun getLink(LinkId:Int): Link

    suspend fun insertAdminLink(adminLink: AdminLink)
    fun getAdminLinks():Resource<LiveData<List<AdminLink>>>
    suspend fun deleteAdminLink(adminLink: AdminLink)
    suspend fun getAdminLink(AdminLinkId:Int): AdminLink

    suspend fun insertOrder1(order1: Order1)
    fun getOrder1s():Resource<LiveData<List<Order1>>>
    suspend fun deleteOrder1(order1: Order1)
    suspend fun getOrder1(Order1Id:Int): Order1

    suspend fun insertOrder2(order2: Order2)
    fun getOrder2s():Resource<LiveData<List<Order2>>>
    suspend fun deleteOrder2(order2: Order2)
    suspend fun getOrder2(Order2Id:Int): Order2

    suspend fun insertOrder3(order3: Order3)
    fun getOrder3s():Resource<LiveData<List<Order3>>>
    suspend fun deleteOrder3(order3: Order3)
    suspend fun getOrder3(Order3Id:Int): Order3

    suspend fun insertOrder4(order4: Order4)
    fun getOrder4s():Resource<LiveData<List<Order4>>>
    suspend fun deleteOrder4(order4: Order4)
    suspend fun getOrder4(Order4Id:Int): Order4

    suspend fun insertOrder5(order5: Order5)
    fun getOrder5s():Resource<LiveData<List<Order5>>>
    suspend fun deleteOrder5(order5: Order5)
    suspend fun getOrder5(Order5Id:Int): Order5

    suspend fun insertOrder6(order6: Order6)
    fun getOrder6s():Resource<LiveData<List<Order6>>>
    suspend fun deleteOrder6(order6: Order6)
    suspend fun getOrder6(Order6Id:Int): Order6

    suspend fun insertOrder7(order7: Order7)
    fun getOrder7s():Resource<LiveData<List<Order7>>>
    suspend fun deleteOrder7(order7: Order7)
    suspend fun getOrder7(Order7Id:Int): Order7

    suspend fun insertOrder8(order8: Order8)
    fun getOrder8s():Resource<LiveData<List<Order8>>>
    suspend fun deleteOrder8(order8: Order8)
    suspend fun getOrder8(Order8Id:Int): Order8

    suspend fun insertBalanceTry(balanceTry: BalanceTry)
   suspend fun getBalanceTry():List<BalanceTry>

    suspend fun insertBalanceAzn(balanceAzn: BalanceAzn)
   suspend fun getBalanceAzn():List<BalanceAzn>

    suspend fun insertBalanceTotalTry(balanceTotalTry: BalanceTotalTry)
    suspend fun updateBalanceTotalTry(balanceTotalTry: BalanceTotalTry)
   suspend fun getBalanceTotalTry():BalanceTotalTry

    suspend fun insertBalanceTotalAzn(balanceTotalAzn: BalanceTotalAzn)
    suspend fun updateBalanceTotalAzn(balanceTotalAzn: BalanceTotalAzn)
   suspend fun getBalanceTotalAzn():BalanceTotalAzn

    suspend fun insertDebt(debt: Debt)
    suspend fun deleteDebt(debt: Debt)
    fun getDebt():Resource<LiveData<List<Debt>>>
    suspend fun deleteDebts()

    suspend fun insertDebtHistory(debtHistory: DebtHistory)
    fun getDebtHistory():Resource<LiveData<List<DebtHistory>>>

    suspend fun insertDebtTotal(debtTotal: DebtTotal)
    suspend fun updateDebtTotal(debtTotal: DebtTotal)
    fun getDebtTotal():Resource<LiveData<List<DebtTotal>>>

    suspend fun insertZipexMoney(zipexMoneyDepot: ZipexMoneyDepot)
    fun getZipexMoney():LiveData<List<ZipexMoneyDepot>>
    suspend fun updateZipexMoney(zipexMoneyDepot: ZipexMoneyDepot)


}