package uz.gita.contactappcompose.ui.viewmodel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.contactappcompose.data.common.ContactData
import uz.gita.contactappcompose.domain.repository.AppRepository
import uz.gita.contactappcompose.ui.viewmodel.HomeViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val repository: AppRepository
) : HomeViewModel, ViewModel() {

    override val contactsLiveData: LiveData<List<ContactData>>
        get() = repository.retrieveAllContacts().asLiveData()

    override fun delete(contactData: ContactData) {
        repository.delete(contactData)
    }

    override fun update(contactData: ContactData) {
        repository.update(contactData)
    }
}