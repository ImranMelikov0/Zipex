package com.imranmelikov.zipex.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BalanceUsd(val history:String,val amount:Double,val balance:Double) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int?=null
}