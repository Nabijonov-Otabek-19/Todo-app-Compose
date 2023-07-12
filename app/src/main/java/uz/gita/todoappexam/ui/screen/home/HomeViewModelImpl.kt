package uz.gita.todoappexam.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.todoappexam.domain.repository.AppRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val repository: AppRepository,
    private val direction: HomeDirection
) : HomeViewContract.ViewModel, ViewModel() {

    override val container =
        container<HomeViewContract.UIState, HomeViewContract.SideEffect>(HomeViewContract.UIState.Loading)

    override fun onEventDispatcher(intent: HomeViewContract.Intent) {
        when (intent) {
            HomeViewContract.Intent.LoadTodos -> {
                repository.retrieveAllContacts().onEach {
                    intent { reduce { HomeViewContract.UIState.PrepareData(it) } }
                }.launchIn(viewModelScope)
            }

            is HomeViewContract.Intent.UpdateState -> {
                repository.updateCompletion(intent.state, intent.todoId)
            }

            is HomeViewContract.Intent.Delete -> {
                repository.delete(intent.contact)
            }

            is HomeViewContract.Intent.OpenEditContact -> {
                viewModelScope.launch {
                    direction.navigateToAddEditScreen(data = intent.updateData)
                }
            }

            is HomeViewContract.Intent.OpenAddContact -> {
                viewModelScope.launch {
                    direction.navigateToAddEditScreen(data = null)
                }
            }
        }
    }
}