package com.imranmelikov.zipex.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class BalanceTry(val history:String,val amount:Int,val balance:Int) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int?=null
}