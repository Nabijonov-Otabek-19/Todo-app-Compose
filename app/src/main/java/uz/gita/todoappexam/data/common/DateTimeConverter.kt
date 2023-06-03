package uz.gita.todoappexam.data.common

import androidx.room.TypeConverter
import java.time.LocalDate
import java.util.Date

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