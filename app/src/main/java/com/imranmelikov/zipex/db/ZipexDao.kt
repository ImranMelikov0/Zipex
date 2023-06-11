package com.imranmelikov.zipex.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
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

@Dao
interface ZipexDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: News)
    @Query("SELECT * FROM News")
    suspend fun getNews():List<News>
    @Query("SELECT * FROM News WHERE uuid=:NewsId")
    suspend fun getNew(NewsId:Int):News

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotification(notification: Notification)
    @Query("SELECT * FROM Notification")
    fun getNotifications():LiveData<List<Notification>>
    @Delete
    suspend fun deleteNotification(notification: Notification)
    @Query("SELECT * FROM Notification WHERE uuid=:NotificationId")
    suspend fun getNotification(NotificationId:Int):Notification

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLink(link: Link)
    @Query("SELECT * FROM Link")
    fun getLinks():LiveData<List<Link>>
    @Delete
    suspend fun deleteLink(link: Link)
    @Update
    suspend fun updateLink(link: Link)
    @Query("SELECT * FROM Link WHERE uuid=:LinkId")
    suspend fun getLink(LinkId:Int):Link

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAdminLink(adminLink: AdminLink)
    @Query("SELECT * FROM AdminLink")
    fun getAdminLinks():LiveData<List<AdminLink>>
    @Delete
    suspend fun deleteAdminLink(adminLink: AdminLink)
    @Query("SELECT * FROM AdminLink WHERE uuid=:AdminLinkId")
    suspend fun getAdminLink(AdminLinkId:Int):AdminLink

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder1(order1: Order1)
    @Query("SELECT * FROM Order1")
    fun getOrder1s():LiveData<List<Order1>>
    @Delete
    suspend fun deleteOrder1(order1: Order1)
    @Query("SELECT * FROM Order1 WHERE uuid=:Order1Id")
    suspend fun getOrder1(Order1Id:Int):Order1

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder2(order2: Order2)
    @Query("SELECT * FROM Order2")
    fun getOrder2s():LiveData<List<Order2>>
    @Delete
    suspend fun deleteOrder2(order2: Order2)
    @Query("SELECT * FROM Order2 WHERE uuid=:Order2Id")
    suspend fun getOrder2(Order2Id:Int):Order2

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder3(order3: Order3)
    @Query("SELECT * FROM Order3")
    fun getOrder3s():LiveData<List<Order3>>
    @Delete
    suspend fun deleteOrder3(order3: Order3)
    @Query("SELECT * FROM Order3 WHERE uuid=:Order3Id")
    suspend fun getOrder3(Order3Id:Int):Order3

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder4(order4: Order4)
    @Query("SELECT * FROM Order4")
    fun getOrder4s():LiveData<List<Order4>>
    @Delete
    suspend fun deleteOrder4(order4: Order4)
    @Query("SELECT * FROM Order4 WHERE uuid=:Order4Id")
    suspend fun getOrder4(Order4Id:Int):Order4

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder5(order5: Order5)
    @Query("SELECT * FROM Order5")
    fun getOrder5s():LiveData<List<Order5>>
    @Delete
    suspend fun deleteOrder5(order5: Order5)
    @Query("SELECT * FROM Order5 WHERE uuid=:Order5Id")
    suspend fun getOrder5(Order5Id:Int):Order5

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder6(order6: Order6)
    @Query("SELECT * FROM Order6")
    fun getOrder6s():LiveData<List<Order6>>
    @Delete
    suspend fun deleteOrder6(order6: Order6)
    @Query("SELECT * FROM Order6 WHERE uuid=:Order6Id")
    suspend fun getOrder6(Order6Id:Int):Order6

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder7(order7: Order7)
    @Query("SELECT * FROM Order7")
    fun getOrder7s():LiveData<List<Order7>>
    @Delete
    suspend fun deleteOrder7(order7: Order7)
    @Query("SELECT * FROM Order7 WHERE uuid=:Order7Id")
    suspend fun getOrder7(Order7Id:Int):Order7

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder8(order8: Order8)
    @Query("SELECT * FROM Order8")
    fun getOrder8s():LiveData<List<Order8>>
    @Delete
    suspend fun deleteOrder8(order8: Order8)
    @Query("SELECT * FROM Order8 WHERE uuid=:Order8Id")
    suspend fun getOrder8(Order8Id:Int):Order8

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBalanceTry(balanceTry: BalanceTry)
    @Query("SELECT * FROM BalanceTry")
    fun getBalanceTry():LiveData<List<BalanceTry>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBalanceAzn(balanceAzn: BalanceAzn)
    @Query("SELECT * FROM BalanceAzn")
    fun getBalanceAzn():LiveData<List<BalanceAzn>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBalanceTotalTry(balanceTotalTry: BalanceTotalTry)
    @Update
    suspend fun updateBalanceTotalTry(balanceTotalTry: BalanceTotalTry)
    @Query("SELECT * FROM BalanceTotalTry")
    fun getBalanceTotalTry():LiveData<List<BalanceTotalTry>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBalanceTotalAzn(balanceTotalAzn: BalanceTotalAzn)
    @Update
    suspend fun updateBalanceTotalAzn(balanceTotalAzn: BalanceTotalAzn)
    @Query("SELECT * FROM BalanceTotalAzn")
    fun getBalanceTotalAzn():LiveData<List<BalanceTotalAzn>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDebt(debt: Debt)
    @Delete
    suspend fun deleteDebt(debt: Debt)
    @Query("SELECT * FROM Debt")
    fun getDebt():LiveData<List<Debt>>
    @Query("DELETE FROM Debt")
    suspend fun deleteDebts()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDebtHistory(debtHistory: DebtHistory)
    @Query("SELECT * FROM DebtHistory")
    fun getDebtHistory():LiveData<List<DebtHistory>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDebtTotal(debtTotal: DebtTotal)
    @Update
    suspend fun updateDebtTotal(debtTotal: DebtTotal)
    @Query("SELECT * FROM DebtTotal")
    fun getDebtTotal():LiveData<List<DebtTotal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertZipexMoney(zipexMoneyDepot: ZipexMoneyDepot)
    @Query("SELECT * FROM ZipexMoneyDepot")
    fun getZipexMoney():LiveData<List<ZipexMoneyDepot>>
    @Update
    suspend fun updateZipexMoney(zipexMoneyDepot: ZipexMoneyDepot)
}