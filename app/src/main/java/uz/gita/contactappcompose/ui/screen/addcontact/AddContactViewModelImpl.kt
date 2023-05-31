package uz.gita.contactappcompose.ui.screen.addcontact

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.contactappcompose.domain.repository.AppRepository
import javax.inject.Inject


@HiltViewModel
class AddContactViewModelImpl @Inject constructor(
    private val repository: AppRepository
) : AddEditContract.ViewModel, ViewModel() {

    override fun onEventDispatcher(intent: AddEditContract.Intent) {
        when (intent) {
            is AddEditContract.Intent.AddContact -> repository.add(intent.addContactData)
            is AddEditContract.Intent.UpdateContact -> repository.update(intent.updateContactData)
        }
    }
}