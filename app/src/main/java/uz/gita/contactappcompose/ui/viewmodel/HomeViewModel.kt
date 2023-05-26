package uz.gita.contactappcompose.ui.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.contactappcompose.data.common.ContactData

interface HomeViewModel {

    val contactsLiveData: LiveData<List<ContactData>>

    fun delete(contactData: ContactData)

    fun update(contactData: ContactData)
}