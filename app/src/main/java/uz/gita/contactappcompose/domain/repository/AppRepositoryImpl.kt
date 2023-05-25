package uz.gita.contactappcompose.domain.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import uz.gita.contactappcompose.data.common.ContactData
import uz.gita.contactappcompose.data.source.local.dao.ContactDao
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val dao: ContactDao
) : AppRepository {

    override fun add(contactData: ContactData) {
        dao.add(contactData.toEntity())
    }

    override fun delete(contactData: ContactData) {
        dao.delete(contactData.toEntity())
    }

    override fun update(contactData: ContactData) {
        dao.update(contactData.toEntity())
    }

    override suspend fun retrieveAllContacts(): Flow<List<ContactData>> = flow {
        dao.retrieveAllContacts().map { list ->
            emit(list.map { it.toData() })
        }
    }
}