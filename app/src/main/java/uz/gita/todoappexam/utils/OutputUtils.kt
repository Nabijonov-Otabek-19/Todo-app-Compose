package uz.gita.todoappexam.utils

import android.content.Context
import android.util.Log
import android.widget.Toast

fun logger(msg: String) {
    Log.d("AAA", msg)
}

fun toast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}