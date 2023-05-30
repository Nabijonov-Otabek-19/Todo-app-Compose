package uz.gita.contactappcompose.ui.viewmodel

import uz.gita.contactappcompose.data.common.ContactData

interface AddEditContract {
    sealed interface Intent {
        class AddContact(val addContactData: ContactData) : Intent
        class UpdateContact(val updateContactData: ContactData) : Intent
    }

    interface ViewModel {
        fun onEventDispatcher(intent: Intent)
    }
}