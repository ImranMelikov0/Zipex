package com.imranmelikov.zipex.db

import androidx.room.Database
import androidx.room.RoomDatabase
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

@Database([News::class
    , Notification::class
    , Link::class
    , AdminLink::class
    , Order1::class
    , Order2::class
    , Order3::class
    , Order4::class
    , Order5::class
    , Order6::class
    , Order7::class
    , Order8::class
    , Order9::class
    , BalanceTotalAzn::class
    , BalanceAzn::class
    , BalanceTry::class
    , BalanceTotalTry::class
    , BalanceUsd::class
    , BalanceTotalUsd::class
    , Debt::class
    , DebtTotal::class
    , DebtHistory::class
    , ZipexMoneyDepot::class],
    version = 1)
abstract class ProjectZipexDatabase:RoomDatabase() {
    abstract fun projectZipexDao():ProjectZipexDao
}