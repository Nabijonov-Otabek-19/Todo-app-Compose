package uz.nabijonov.otabek.todoapp_bek.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.nabijonov.otabek.todoapp_bek.data.common.TodoData

interface AppRepository {

    fun add(todoData: TodoData)
    fun update(todoData: TodoData)
    fun delete(todoData: TodoData)
    fun retrieveAllContacts(): Flow<List<TodoData>>
    fun updateCompletion(state: Boolean, todoId: Long)
}