package com.imranmelikov.zipex.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Link(val url:String,val category:String,val count:Int,val color:String,val size:String,val price:Int,val comment:String,val history:String) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int?=null
}