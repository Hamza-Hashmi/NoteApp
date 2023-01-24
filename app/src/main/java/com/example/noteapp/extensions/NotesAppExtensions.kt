package com.example.noteapp.extensions

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.Toast

const val databaseName = "NotesAppDatabase"

fun Activity.sendUserToActivity(activityClass: Class<out Activity?>) {

    val intent = Intent(this, activityClass)
    startActivity(intent)
}

fun Activity.showMessage(msg:String){
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun View.beVisible(){
    this.visibility =View.VISIBLE
}
fun View.beGone(){
    this.visibility = View.GONE
}
