package com.jefftrotz.fitnesstracker.util

import androidx.room.TypeConverter
import java.util.*

class DateConverter {
    @TypeConverter
    fun stringFromDate(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun dateFromTimestamp(timestamp: Long): Date {
        return Date(timestamp)
    }
}