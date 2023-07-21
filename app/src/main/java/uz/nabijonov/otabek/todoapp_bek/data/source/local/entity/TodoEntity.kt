package uz.nabijonov.otabek.todoapp_bek.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.nabijonov.otabek.todoapp_bek.data.common.TodoData
import java.util.UUID

@Entity(tableName = "todos")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String,
    val date: String,
    val time: String,
    val category : String,
    val isDone : Boolean,
    val color : Int,
    val workId: UUID
) {
    fun toData() = TodoData(
        id, title, description, date, time, category,isDone,color, workId
    )
}