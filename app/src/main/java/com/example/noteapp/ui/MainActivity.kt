package com.example.noteapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.LifecycleOwner
import com.example.noteapp.adapters.NotesAdapter
import com.example.noteapp.databinding.ActivityMainBinding
import com.example.noteapp.extensions.beGone
import com.example.noteapp.extensions.beVisible
import com.example.noteapp.extensions.sendUserToActivity
import com.example.noteapp.extensions.showMessage
import com.example.noteapp.utils.NoteState
import com.example.noteapp.viewModels.MainScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private  val TAG = "MainActivity"
    private val viewModel: MainScreenViewModel by viewModels()
    @Inject
    lateinit var adapter: NotesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleResponse()
        initClicks()
    }

    private fun initClicks() {
        binding.apply {
            btnAddNotes.setOnClickListener{
                sendUserToActivity(AddNotesActivity::class.java)
            }
        }

        adapter.deleteNote = { note,pos ->
            viewModel.deleteNote(note)
            adapter.delteItem(note,pos)
            showMessage("Note Deleted")
        }
    }

    private fun handleResponse() {
        viewModel.getNotes()
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.noteStateFlow.collect {
                when (it) {
                    is NoteState.Loading -> {
                        binding.progressBar.beVisible()
                    }
                    is NoteState.Success -> {
                        if (it.response.size > 0){
                            binding.apply {
                                progressBar.beGone()
                                notesRv.beVisible()
                                tvNoData.beGone()
                            }
                            adapter.setData(it.response)
                                binding.notesRv.adapter = adapter
                                adapter.notifyDataSetChanged()
                        }else{
                            binding.apply {
                                progressBar.beGone()
                                notesRv.beGone()
                                tvNoData.beVisible()
                            }
                        }
                    }
                    is NoteState.Faliure -> {
                        binding.apply {
                            progressBar.beGone()
                            notesRv.beGone()
                            tvNoData.beVisible()
                        }
                        showMessage("Error: ${it.error}")
                    }
                    is NoteState.Empty -> {
                        binding.apply {
                            progressBar.beGone()
                            notesRv.beGone()
                            tvNoData.beVisible()
                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        handleResponse()
    }
}

