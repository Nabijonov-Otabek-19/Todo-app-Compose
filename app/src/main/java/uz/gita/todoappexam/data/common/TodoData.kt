package uz.gita.todoappexam.data.common

import uz.gita.todoappexam.data.source.local.entity.TodoEntity
import java.time.LocalDate
import java.time.LocalTime
import java.util.Date
import java.util.TimeZone

data class TodoData(
    val id: Long = 0,
    val title: String,
    val description: String,
    val date: String,
    val time: String
) {
    fun toEntity() = TodoEntity(
        id, title, description, date, time
    )
}