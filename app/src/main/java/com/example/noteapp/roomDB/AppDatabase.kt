
package com.example.noteapp.roomDB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteapp.models.Notes


@Database(entities = [Notes::class],version = 1,exportSchema = false)
abstract class AppDatabase:RoomDatabase(){

        abstract fun getDao():NotesDAO

}