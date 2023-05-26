package uz.gita.contactappcompose.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.gita.contactappcompose.data.common.ContactData

@Entity(tableName = "contacts")
data class ContactEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val fname: String,
    val lname: String,
    val phone: String
) {
    fun toData() = ContactData(
        id, fname, lname, phone
    )
}