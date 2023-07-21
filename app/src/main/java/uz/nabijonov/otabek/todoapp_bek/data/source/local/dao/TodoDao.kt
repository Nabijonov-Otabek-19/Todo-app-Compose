package uz.nabijonov.otabek.todoapp_bek.data.source.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import uz.nabijonov.otabek.todoapp_bek.data.source.local.entity.TodoEntity

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

    @Query("Update todos set isDone = :state WHERE id = :todoId")
    fun updateCompletion(state: Boolean, todoId : Long)
}