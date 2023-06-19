package com.imranmelikov.zipex.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BalanceTotalAzn(val balanceTotal:Double) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int?=null
}