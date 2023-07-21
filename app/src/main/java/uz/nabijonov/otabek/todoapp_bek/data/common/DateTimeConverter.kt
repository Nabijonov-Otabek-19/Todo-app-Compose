package uz.nabijonov.otabek.todoapp_bek.data.common

import androidx.room.TypeConverter
import java.time.LocalDate

object DateTimeConverter {
    @TypeConverter
    fun toDate(value: Long?): LocalDate? {
        return value?.let { LocalDate.ofEpochDay(it) }
    }

    @TypeConverter
    fun toLong(date: LocalDate?): Long? {
        return date?.toEpochDay()
    }
}