package com.imranmelikov.zipex.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class DebtHistory(val history:String,val amount:Double) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int?=null
}