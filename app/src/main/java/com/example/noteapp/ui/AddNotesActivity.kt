package com.example.noteapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.noteapp.databinding.ActivityAddNotesBinding
import com.example.noteapp.extensions.showMessage
import com.example.noteapp.models.Notes
import com.example.noteapp.viewModels.MainScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class AddNotesActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddNotesBinding
    val viewModel:MainScreenViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initClick()
    }

    @SuppressLint("SimpleDateFormat")
    private fun initClick() {
        binding.apply {
            btnSave.setOnClickListener{
                if (TextUtils.isEmpty(edtNote.text.toString())){
                    showMessage("Please enter a note.")
                    return@setOnClickListener
                }
                else if(TextUtils.isEmpty(title.text.toString())){
                    showMessage("Please enter a title.")
                    return@setOnClickListener
                }else{
                      val note = Notes()
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())
                    note.title = title.text.toString()
                    note.dateTime = currentDateAndTime
                    note.note = edtNote.text.toString()
                     viewModel.insertNote(note)
                    title.setText("")
                     edtNote.setText("")
                    showMessage("Note added sucessfully!")
                }
            }
        }
    }

    override fun onBackPressed() {
        finish()
        //onBackPressedDispatcher.onBackPressed()
    }
}
