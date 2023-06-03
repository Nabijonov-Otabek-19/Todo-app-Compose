package uz.gita.todoappexam.ui.screen.addtodo

import uz.gita.todoappexam.data.common.TodoData

interface AddEditContract {

    sealed interface Intent {
        class AddContact(val addTodoData: TodoData) : Intent
        class UpdateContact(val updateTodoData: TodoData) : Intent
    }

    interface ViewModel {
        fun onEventDispatcher(intent: Intent)
    }
}