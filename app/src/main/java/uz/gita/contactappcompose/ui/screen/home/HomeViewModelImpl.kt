package uz.gita.contactappcompose.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import uz.gita.contactappcompose.domain.repository.AppRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val repository: AppRepository
) : HomeViewContract.ViewModel, ViewModel() {

    override val uiState = MutableStateFlow(HomeViewContract.UiState())

    init {
        repository.retrieveAllContacts()
            .onEach { contacts -> uiState.update { it.copy(contacts = contacts) } }
            .launchIn(viewModelScope)
    }

    override fun onEventDispatcher(intent: HomeViewContract.Intent) {
        when (intent) {
            is HomeViewContract.Intent.Delete -> repository.delete(intent.contact)
            is HomeViewContract.Intent.OpenEditContact -> uiState.update {
                it.copy(
                    updateData = intent.updateData,
                    editContactState = true
                )
            }

            is HomeViewContract.Intent.OpenAddContact -> uiState.update { it.copy(addContactState = true) }
            is HomeViewContract.Intent.CloseAddContact -> uiState.update {
                it.copy(
                    addContactState = false,
                    editContactState = false
                )
            }
        }
    }
}