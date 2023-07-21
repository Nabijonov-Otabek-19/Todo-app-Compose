package uz.nabijonov.otabek.todoapp_bek.ui.screen.add

import uz.nabijonov.otabek.todoapp_bek.data.common.TodoData

interface AddEditContract {

    interface ViewModel {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface Intent {
        class AddContact(val addTodoData: TodoData) : Intent
        class UpdateContact(val updateTodoData: TodoData) : Intent
    }
}