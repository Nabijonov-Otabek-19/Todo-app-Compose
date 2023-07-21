package uz.nabijonov.otabek.todoapp_bek.data.common

import uz.nabijonov.otabek.todoapp_bek.data.source.local.entity.TodoEntity
import java.util.UUID

data class TodoData(
    val id: Long = 0,
    val title: String,
    val description: String,
    val date: String,
    val time: String,
    val category: String,
    val isDone : Boolean,
    val color : Int,
    val workId: UUID
) {
    fun toEntity() = TodoEntity(
        id, title, description, date, time, category, isDone,color, workId
    )
}