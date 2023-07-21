package uz.nabijonov.otabek.todoapp_bek.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import uz.nabijonov.otabek.todoapp_bek.R

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
    R.color.pink,
    R.color.orange,
    R.color.blue
)

val categories = listOf(
    "Home", "Work", "Study", "Sport", "Social", "Grocery"
)