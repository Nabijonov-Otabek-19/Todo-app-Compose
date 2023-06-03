package uz.gita.todoappexam.data.source.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import uz.gita.todoappexam.data.source.local.entity.TodoEntity

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun add(todoEntity: TodoEntity)

    @Delete
    fun delete(todoEntity: TodoEntity)

    @Update
    fun update(todoEntity: TodoEntity)

    @Query("Select * from todos")
    fun retrieveAllContacts(): Flow<List<TodoEntity>>
}