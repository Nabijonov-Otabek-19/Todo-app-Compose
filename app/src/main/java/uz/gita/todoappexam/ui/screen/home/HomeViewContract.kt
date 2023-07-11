package uz.gita.todoappexam.ui.screen.home

import org.orbitmvi.orbit.ContainerHost
import uz.gita.todoappexam.data.common.TodoData

interface HomeViewContract {

    interface ViewModel : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState {
        object Loading : UIState
        data class PrepareData(val todos: List<TodoData>) : UIState
    }

    sealed interface SideEffect {
        data class Toast(val message: String) : SideEffect
    }

    sealed interface Intent {
        class OpenEditContact(val updateData: TodoData) : Intent
        class Delete(val contact: TodoData) : Intent
        object OpenAddContact : Intent
        object LoadTodos : Intent
    }

    interface Direction {
        suspend fun navigateToAddEditScreen(data: TodoData?)
    }
}