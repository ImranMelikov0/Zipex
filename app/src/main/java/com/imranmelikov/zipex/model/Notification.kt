package com.imranmelikov.zipex.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Notification(val title:String,val post:String,val titleGray:String) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int?=null
}