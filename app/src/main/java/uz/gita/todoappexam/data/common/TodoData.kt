package uz.gita.todoappexam.data.common

import uz.gita.todoappexam.data.source.local.entity.TodoEntity
import java.util.UUID

data class TodoData(
    val id: Long = 0,
    val title: String,
    val description: String,
    val date: String,
    val time: String,
    val workId: UUID
) {
    fun toEntity() = TodoEntity(
        id, title, description, date, time, workId
    )
}