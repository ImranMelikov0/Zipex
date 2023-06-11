package com.imranmelikov.zipex.model

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class News
    (val title:String,val post:String,val imageUrl:String
) {
        @PrimaryKey(autoGenerate = true)
        var uuid:Int?=null
}