package com.imranmelikov.zipex.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Order9(val url:String, val category:String, val count:Int, val color:String, val size:String, val price:Double, val comment:String, val history:String,
                  val country:String,
                  val sigorta:String,
                  val payment:String, val marketName:String, val marketCode:String, val office:String) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int?=null
}