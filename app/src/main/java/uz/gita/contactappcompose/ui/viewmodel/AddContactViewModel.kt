package uz.gita.contactappcompose.ui.viewmodel

import uz.gita.contactappcompose.data.common.ContactData


interface AddContactViewModel {
    fun addContact(fname: String, lname: String, phone: String)

    fun updateContact(contactData: ContactData)
}