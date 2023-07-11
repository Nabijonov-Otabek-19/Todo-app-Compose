package uz.gita.todoappexam.ui.screen.add

import uz.gita.todoappexam.data.common.TodoData

interface AddEditContract {

    interface ViewModel {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface Intent {
        class AddContact(val addTodoData: TodoData) : Intent
        class UpdateContact(val updateTodoData: TodoData) : Intent
    }
}