package uz.gita.todoappexam.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.gita.todoappexam.data.common.TodoData
import java.util.UUID

@Entity(tableName = "todos")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String,
    val date: String,
    val time: String,
    val workId: UUID
) {
    fun toData() = TodoData(
        id, title, description, date, time, workId
    )
}