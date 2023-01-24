package com.example.noteapp.viewModels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.repository.DatabaseRepo
import com.example.noteapp.utils.NoteState
import com.example.noteapp.models.Notes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MainScreenViewModel @Inject constructor(val repo:DatabaseRepo):ViewModel() {
 private val _noteStateFlow : MutableStateFlow<NoteState> = MutableStateFlow(NoteState.Empty)
    val noteStateFlow:StateFlow<NoteState> = _noteStateFlow

    fun getNotes() = viewModelScope.launch {
        repo.getData()
            .onStart {
                _noteStateFlow.value = NoteState.Loading
            }.catch { e->
                _noteStateFlow.value = NoteState.Faliure(e)
            }.collect{ response ->
                _noteStateFlow.value = NoteState.Success(response)
            }
    }

    fun insertNote(note:Notes) = viewModelScope.launch {
        repo.insertNote(note)
    }

    fun deleteNote(note: Notes) = viewModelScope.launch {
        repo.deleteNote(note)
    }

 }