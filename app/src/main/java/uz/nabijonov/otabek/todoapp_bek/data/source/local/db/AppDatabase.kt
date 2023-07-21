package uz.nabijonov.otabek.todoapp_bek.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.nabijonov.otabek.todoapp_bek.data.source.local.dao.TodoDao
import uz.nabijonov.otabek.todoapp_bek.data.source.local.entity.TodoEntity

@Database(entities = [TodoEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getContactDao(): TodoDao

}