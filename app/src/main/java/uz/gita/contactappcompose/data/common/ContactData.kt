package uz.gita.contactappcompose.data.common

import uz.gita.contactappcompose.data.source.local.entity.ContactEntity

data class ContactData(
    val id: Long = 0,
    val firstName: String,
    val lastName: String,
    val phone: String
){
    fun toEntity() = ContactEntity(
        id, firstName, lastName, phone
    )
}