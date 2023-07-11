package uz.gita.todoappexam.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import uz.gita.todoappexam.R

fun logger(msg: String) {
    Log.d("AAA", msg)
}

fun toast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

val colors = listOf(
    R.color.yellow,
    R.color.red,
    R.color.green,
    R.color.pink
)