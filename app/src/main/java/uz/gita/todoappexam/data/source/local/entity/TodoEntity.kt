package uz.gita.todoappexam.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.gita.todoappexam.data.common.DateTimeConverter
import uz.gita.todoappexam.data.common.TodoData
import java.time.LocalDate
import java.time.LocalTime
import java.util.Date
import java.util.TimeZone

@Entity(tableName = "todos")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String,
    val date: String,
    val time: String
) {
    fun toData() = TodoData(
        id, title, description, date, time
    )
}