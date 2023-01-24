package com.example.noteapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Notes(
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var title:String ="",
    var note:String = "",
    var dateTime:String = ""
) {
}