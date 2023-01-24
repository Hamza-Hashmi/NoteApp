
package com.example.noteapp.roomDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.noteapp.models.Notes
@Dao
interface NotesDAO {
    @Query("SELECT * FROM Notes")
    fun getAll(): List<Notes>

    @Insert
    suspend fun insertNote( note: Notes)

    @Delete
    suspend fun delete(note: Notes)
}