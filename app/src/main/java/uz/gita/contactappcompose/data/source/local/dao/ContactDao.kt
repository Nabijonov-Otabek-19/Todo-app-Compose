package uz.gita.contactappcompose.data.source.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import uz.gita.contactappcompose.data.source.local.entity.ContactEntity

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun add(contactEntity: ContactEntity)

    @Delete
    fun delete(contactEntity: ContactEntity)

    @Update
    fun update(contactEntity: ContactEntity)

    @Query("Select * from contacts")
    fun retrieveAllContacts(): Flow<List<ContactEntity>>
}