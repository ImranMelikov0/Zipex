package com.imranmelikov.zipex.model

import android.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Order7(val url:String, val category:String, val count:Int, val color:String, val size:String, val price:Double, val comment:String, val history:String, val marketName:String, val marketCode:String, val office:String) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int?=null
}