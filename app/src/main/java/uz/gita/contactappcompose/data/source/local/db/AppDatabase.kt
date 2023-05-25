package uz.gita.contactappcompose.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.gita.contactappcompose.data.source.local.dao.ContactDao
import uz.gita.contactappcompose.data.source.local.entity.ContactEntity

@Database(entities = [ContactEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getContactDao(): ContactDao


}