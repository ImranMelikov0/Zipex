package com.imranmelikov.zipex.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BalanceTotalTry(val balanceTotal:Int) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int?=null
}