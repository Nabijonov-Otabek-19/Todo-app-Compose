package uz.gita.todoappexam.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.todoappexam.data.common.TodoData

interface AppRepository {

    fun add(todoData: TodoData)
    fun update(todoData: TodoData)
    fun delete(todoData: TodoData)
    fun retrieveAllContacts(): Flow<List<TodoData>>
    fun updateCompletion(state: Boolean, todoId: Long)
}