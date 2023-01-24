package com.example.noteapp.repository

import com.example.noteapp.models.Notes
import com.example.noteapp.roomDB.AppDatabase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DatabaseRepo @Inject constructor(val db:AppDatabase) {

    fun getData(): Flow<ArrayList<Notes>> = flow {
        emit(db.getDao().getAll() as ArrayList<Notes>)
    }.flowOn(Dispatchers.IO)


    suspend fun insertNote(item: Notes){
            db.getDao().insertNote(item)
    }

    suspend fun deleteNote(note:Notes){
        db.getDao().delete(note)
    }

}