package uz.gita.todoappexam.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.gita.todoappexam.data.source.local.dao.TodoDao
import uz.gita.todoappexam.data.source.local.entity.TodoEntity

@Database(entities = [TodoEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getContactDao(): TodoDao

}