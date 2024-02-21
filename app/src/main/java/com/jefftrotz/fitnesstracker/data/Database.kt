package com.jefftrotz.fitnesstracker.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jefftrotz.fitnesstracker.model.User
import com.jefftrotz.fitnesstracker.util.converters.DateConverter
import com.jefftrotz.fitnesstracker.util.converters.ListConverter
import com.jefftrotz.fitnesstracker.util.converters.UuidConverter

@Database(entities = [User::class], version = 1, exportSchema = false)
//@TypeConverters(DateConverter::class, UuidConverter::class, ListConverter::class)
abstract class Database : RoomDatabase() {

    abstract fun getDAO(): DAO
}