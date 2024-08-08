package com.jefftrotz.fitnesstracker.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

import com.jefftrotz.fitnesstracker.model.User
import com.jefftrotz.fitnesstracker.util.converters.DateConverter
import com.jefftrotz.fitnesstracker.util.converters.ListConverter
import com.jefftrotz.fitnesstracker.util.converters.UuidConverter

/**
 * Database to store user data on the device.
 * @see DateConverter
 * @see ListConverter
 * @see UuidConverter
 */
@Database(entities = [User::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, ListConverter::class, UuidConverter::class)
abstract class Database: RoomDatabase() {

    /**
     * Gets the [DAO] (data access object).
     * @return [DAO] object.
     * @see DAO
     */
    abstract fun getDAO(): DAO
}