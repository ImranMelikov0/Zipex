package com.imranmelikov.zipex.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Debt(val history:String,val amount:Int) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int?=null
}