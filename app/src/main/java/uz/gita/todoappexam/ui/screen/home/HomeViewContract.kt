package uz.gita.todoappexam.ui.screen.home

import kotlinx.coroutines.flow.StateFlow
import uz.gita.todoappexam.data.common.TodoData

interface HomeViewContract {

    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(
        val contacts: List<TodoData> = listOf(),
        val updateData: TodoData? = null
    )

    sealed interface Intent {
        class OpenEditContact(val updateData: TodoData) : Intent
        class Delete(val contact: TodoData) : Intent
        object OpenAddContact : Intent
    }

    interface Direction {
        suspend fun navigateToAddEditScreen(data: TodoData?)
    }
}