package com.example.noteapp.utils

import com.example.noteapp.models.Notes

sealed class NoteState{
    object Loading:NoteState()
    object Empty:NoteState()
    class Success(val response:ArrayList<Notes>):NoteState()
    class Faliure(val error:Throwable):NoteState()
}
