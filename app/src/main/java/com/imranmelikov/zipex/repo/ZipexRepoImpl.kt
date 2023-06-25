package com.imranmelikov.zipex.repo

import androidx.lifecycle.LiveData
import com.imranmelikov.zipex.db.ProjectZipexDao
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

class ZipexRepoImpl(private val zipexDao: ProjectZipexDao):ZipexRepo {
    override suspend fun insertNews(news: News) {
        zipexDao.insertNews(news)
    }

    override suspend fun getNews(): List<News> {
       return zipexDao.getNews()
    }

    override suspend fun getNew(newsId: Int): News {
        return zipexDao.getNew(newsId)
    }

    override suspend fun insertNotification(notification: Notification) {
      zipexDao.insertNotification(notification)
    }

    override suspend fun updateNotification(notification: Notification) {
        zipexDao.updateNotification(notification)
    }

    override suspend fun getNotifications(): List<Notification> {
       return zipexDao.getNotifications()
    }

    override suspend fun deleteNotification(notification: Notification) {
        zipexDao.deleteNotification(notification)
    }

    override suspend fun getNotification(NotificationId: Int): Notification {
       return zipexDao.getNotification(NotificationId)
    }

    override suspend fun insertLink(link: Link) {
        zipexDao.insertLink(link)
    }

    override suspend fun getLinks(): List<Link> {
        return zipexDao.getLinks()
    }

    override suspend fun deleteLink(link: Link) {
        zipexDao.deleteLink(link)
    }

    override suspend fun updateLink(link: Link) {
     zipexDao.updateLink(link)
    }

    override suspend fun getLink(LinkId: Int): Link {
       return zipexDao.getLink(LinkId)
    }

    override suspend fun insertAdminLink(adminLink: AdminLink) {
        zipexDao.insertAdminLink(adminLink)
    }

    override suspend fun getAdminLinks(): List<AdminLink>{
        return zipexDao.getAdminLinks()
    }

    override suspend fun deleteAdminLink(adminLink: AdminLink) {
        zipexDao.deleteAdminLink(adminLink)
    }

    override suspend fun getAdminLink(AdminLinkId: Int): AdminLink {
        return zipexDao.getAdminLink(AdminLinkId)
    }

    override suspend fun insertOrder1(order1: Order1) {
       zipexDao.insertOrder1(order1)
    }

    override suspend fun getOrder1s(): List<Order1> {
       return zipexDao.getOrder1s()
    }

    override suspend fun deleteOrder1(order1: Order1) {
       zipexDao.deleteOrder1(order1)
    }

    override suspend fun updateOrder1(order1: Order1) {
        zipexDao.updateOrder1(order1)
    }

    override suspend fun getOrder1(Order1Id: Int): Order1 {
        return zipexDao.getOrder1(Order1Id)
    }

    override suspend fun insertOrder2(order2: Order2) {
        zipexDao.insertOrder2(order2)
    }

    override suspend fun getOrder2s(): List<Order2> {
        return zipexDao.getOrder2s()
    }

    override suspend fun deleteOrder2(order2: Order2) {
        zipexDao.deleteOrder2(order2)
    }

    override suspend fun updateOrder2(order2: Order2) {
        zipexDao.updateOrder2(order2)
    }

    override suspend fun getOrder2(Order2Id: Int): Order2 {
        return zipexDao.getOrder2(Order2Id)
    }

    override suspend fun insertOrder3(order3: Order3) {
        zipexDao.insertOrder3(order3)
    }

    override suspend fun getOrder3s(): List<Order3> {
        return zipexDao.getOrder3s()
    }

    override suspend fun deleteOrder3(order3: Order3) {
        zipexDao.deleteOrder3(order3)
    }

    override suspend fun updateOrder3(order3: Order3) {
       zipexDao.updateOrder3(order3)
    }

    override suspend fun getOrder3(Order3Id: Int): Order3 {
        return zipexDao.getOrder3(Order3Id)
    }

    override suspend fun insertOrder4(order4: Order4) {
        zipexDao.insertOrder4(order4)
    }

    override suspend fun getOrder4s(): List<Order4> {
        return zipexDao.getOrder4s()
    }

    override suspend fun deleteOrder4(order4: Order4) {
        zipexDao.deleteOrder4(order4)
    }

    override suspend fun getOrder4(Order4Id: Int): Order4 {
       return zipexDao.getOrder4(Order4Id)
    }

    override suspend fun insertOrder5(order5: Order5) {
        zipexDao.insertOrder5(order5)
    }

    override suspend fun getOrder5s(): List<Order5> {
        return zipexDao.getOrder5s()
    }

    override suspend fun deleteOrder5(order5: Order5) {
        zipexDao.deleteOrder5(order5)
    }

    override suspend fun getOrder5(Order5Id: Int): Order5 {
        return zipexDao.getOrder5(Order5Id)
    }

    override suspend fun insertOrder6(order6: Order6) {
        zipexDao.insertOrder6(order6)
    }

    override suspend fun getOrder6s(): List<Order6> {
        return zipexDao.getOrder6s()
    }

    override suspend fun deleteOrder6(order6: Order6) {
        zipexDao.deleteOrder6(order6)
    }

    override suspend fun getOrder6(Order6Id: Int): Order6 {
        return zipexDao.getOrder6(Order6Id)
    }

    override suspend fun insertOrder7(order7: Order7) {
        zipexDao.insertOrder7(order7)
    }

    override suspend fun getOrder7s(): List<Order7> {
        return zipexDao.getOrder7s()
    }

    override suspend fun deleteOrder7(order7: Order7) {
        zipexDao.deleteOrder7(order7)
    }

    override suspend fun getOrder7(Order7Id: Int): Order7 {
        return zipexDao.getOrder7(Order7Id)
    }

    override suspend fun insertOrder8(order8: Order8) {
        zipexDao.insertOrder8(order8)
    }

    override suspend fun getOrder8s(): List<Order8> {
        return zipexDao.getOrder8s()
    }

    override suspend fun deleteOrder8(order8: Order8) {
        zipexDao.deleteOrder8(order8)
    }

    override suspend fun getOrder8(Order8Id: Int): Order8 {
       return zipexDao.getOrder8(Order8Id)
    }

    override suspend fun insertOrder9(order9: Order9) {
        zipexDao.insertOrder9(order9)
    }

    override suspend fun getOrder9s(): List<Order9> {
       return zipexDao.getOrder9s()
    }

    override suspend fun deleteOrder9(order9: Order9) {
        zipexDao.deleteOrder9(order9)
    }

    override suspend fun getOrder9(Order9Id: Int): Order9 {
       return zipexDao.getOrder9(Order9Id)
    }

    override suspend fun insertBalanceTry(balanceTry: BalanceTry) {
       zipexDao.insertBalanceTry(balanceTry)
    }

    override suspend fun getBalanceTry(): List<BalanceTry> {
        return zipexDao.getBalanceTry()
    }

    override suspend fun insertBalanceAzn(balanceAzn: BalanceAzn) {
        zipexDao.insertBalanceAzn(balanceAzn)
    }

    override suspend fun getBalanceAzn(): List<BalanceAzn>{
        return zipexDao.getBalanceAzn()
    }

    override suspend fun insertBalanceTotalTry(balanceTotalTry: BalanceTotalTry) {
        zipexDao.insertBalanceTotalTry(balanceTotalTry)
    }

    override suspend fun updateBalanceTotalTry(balanceTotalTry: BalanceTotalTry) {
        zipexDao.updateBalanceTotalTry(balanceTotalTry)
    }

    override suspend fun getBalanceTotalTry(): BalanceTotalTry{
        return zipexDao.getBalanceTotalTry()
    }

    override suspend fun insertBalanceTotalAzn(balanceTotalAzn: BalanceTotalAzn) {
        zipexDao.insertBalanceTotalAzn(balanceTotalAzn)
    }

    override suspend fun updateBalanceTotalAzn(balanceTotalAzn: BalanceTotalAzn) {
        zipexDao.updateBalanceTotalAzn(balanceTotalAzn)
    }

    override suspend fun getBalanceTotalAzn(): BalanceTotalAzn {
        return zipexDao.getBalanceTotalAzn()
    }

    override suspend fun insertBalanceUsd(balanceUsd: BalanceUsd) {
        zipexDao.insertBalanceUsd(balanceUsd)
    }

    override suspend fun getBalanceUsd(): List<BalanceUsd> {
        return zipexDao.getBalanceUsd()
    }

    override suspend fun insertBalanceTotalUsd(balanceTotalUsd: BalanceTotalUsd) {
        zipexDao.insertBalanceTotalUsd(balanceTotalUsd)
    }

    override suspend fun updateBalanceTotalUsd(balanceTotalUsd: BalanceTotalUsd) {
        zipexDao.updateBalanceTotalUsd(balanceTotalUsd)
    }

    override suspend fun getBalanceTotalUsd(): BalanceTotalUsd {
       return zipexDao.getBalanceTotalUsd()
    }

    override suspend fun insertDebt(debt: Debt) {
        zipexDao.insertDebt(debt)
    }

    override suspend fun deleteDebt(debt: Debt) {
        zipexDao.deleteDebt(debt)
    }

    override suspend fun getDebt(): List<Debt>{
        return zipexDao.getDebt()
    }

    override suspend fun deleteDebts() {
        zipexDao.deleteDebts()
    }

    override suspend fun insertDebtHistory(debtHistory: DebtHistory) {
        zipexDao.insertDebtHistory(debtHistory)
    }

    override suspend fun getDebtHistory(): List<DebtHistory> {
        return zipexDao.getDebtHistory()
    }

    override suspend fun insertDebtTotal(debtTotal: DebtTotal) {
        zipexDao.insertDebtTotal(debtTotal)
    }

    override suspend fun updateDebtTotal(debtTotal: DebtTotal) {
       zipexDao.updateDebtTotal(debtTotal)
    }

    override suspend fun getDebtTotal(): DebtTotal{
        return zipexDao.getDebtTotal()
    }

    override suspend fun insertZipexMoney(zipexMoneyDepot: ZipexMoneyDepot) {
        zipexDao.insertZipexMoney(zipexMoneyDepot)
    }

    override suspend fun getZipexMoney(): List<ZipexMoneyDepot>{
        return zipexDao.getZipexMoney()
    }

    override suspend fun updateZipexMoney(zipexMoneyDepot: ZipexMoneyDepot) {
        zipexDao.updateZipexMoney(zipexMoneyDepot)
    }
}