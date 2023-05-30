package uz.gita.contactappcompose.ui.viewmodel

import kotlinx.coroutines.flow.StateFlow
import uz.gita.contactappcompose.data.common.ContactData

interface HomeViewContract {
    sealed interface Intent {
        class OpenEditContact(val updateData: ContactData) : Intent
        class Delete(val contact: ContactData) : Intent

        object OpenAddContact : Intent
        object CloseAddContact : Intent
    }

    data class UiState(
        val contacts: List<ContactData> = listOf(),
        val updateData: ContactData? = null,
        val editContactState: Boolean = false,
        val addContactState: Boolean = false
    )

    interface ViewModel {
        val uiState: StateFlow<UiState>

        fun onEventDispatcher(intent: Intent)
    }
}