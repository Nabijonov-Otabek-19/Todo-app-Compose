package uz.gita.contactappcompose.ui.viewmodel.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.contactappcompose.data.common.ContactData
import uz.gita.contactappcompose.domain.repository.AppRepository
import uz.gita.contactappcompose.ui.viewmodel.AddContactViewModel
import javax.inject.Inject


@HiltViewModel
class AddContactViewModelImpl @Inject constructor(
    private val repository: AppRepository
) : AddContactViewModel, ViewModel() {

    override fun addContact(fname: String, lname: String, phone: String) {
        repository.add(ContactData(firstName = fname, lastName = lname, phone = phone))
    }

    override fun updateContact(contactData: ContactData) {
        repository.update(contactData)
    }
}