package com.example.noteapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.databinding.ItemNotesBinding
import com.example.noteapp.models.Notes

class NotesAdapter: RecyclerView.Adapter<NotesAdapter.ItemsViewHolder>() {

     var notesList = ArrayList<Notes>()
    var deleteNote:((note:Notes,positon:Int) -> Unit)? = null
    inner class ItemsViewHolder(val binding:ItemNotesBinding): RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        return ItemsViewHolder(ItemNotesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return if (notesList.size<1){
            0
        }else{
            notesList.size
        }
    }

    fun setData(list:ArrayList<Notes>){
        notesList = list
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        val note = notesList[position]

        holder.binding.apply {
            this.titleTv.text = note.title
            this.bodyTv.text = note.note
            this.dateTimeTv.text = note.dateTime
            this.deleteBtn.setOnClickListener {
                deleteNote?.invoke(note,position)
            }
        }
    }


    fun delteItem(note:Notes,position: Int){
        notesList.remove(note)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position,notesList.size)
    }
}

