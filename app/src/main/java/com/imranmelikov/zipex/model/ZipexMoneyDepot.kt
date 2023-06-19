package com.imranmelikov.zipex.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ZipexMoneyDepot(val moneyDepot:Double) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int?=null
}