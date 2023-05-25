package uz.gita.contactappcompose.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.contactappcompose.data.common.ContactData

interface AppRepository {

    fun add(contactData: ContactData)
    fun delete(contactData: ContactData)
    fun update(contactData: ContactData)
    fun retrieveAllContacts(): Flow<List<ContactData>>
}