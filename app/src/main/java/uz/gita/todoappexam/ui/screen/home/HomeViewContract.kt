package uz.gita.todoappexam.ui.screen.home

import kotlinx.coroutines.flow.StateFlow
import org.orbitmvi.orbit.ContainerHost
import uz.gita.todoappexam.data.common.TodoData

interface HomeViewContract {

    sealed interface Intent {
        class OpenEditContact(val updateData: TodoData) : Intent
        class Delete(val contact: TodoData) : Intent

        object OpenAddContact : Intent
    }

    data class UiState(
        val contacts: List<TodoData> = listOf(),
        val updateData: TodoData? = null
    )

    sealed interface UiState1 {
        data class Contacts(val contacts: List<TodoData> = emptyList()) : UiState1
        data class UpdateData(val updateData: TodoData? = null) : UiState1
    }

    sealed interface SideEffect {

    }

    interface ViewModel1 : ContainerHost<UiState1, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onEventDispatcher(intent: Intent)
    }

    interface Direction {
        suspend fun navigateToAddEditScreen(data: TodoData?)
    }
}