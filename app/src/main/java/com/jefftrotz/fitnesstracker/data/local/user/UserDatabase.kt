package com.jefftrotz.fitnesstracker.data.local.user

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jefftrotz.fitnesstracker.model.User
import com.jefftrotz.fitnesstracker.util.converters.ByteArrayConverter

@Database(entities = [User::class], version = 1, exportSchema = false)
@TypeConverters(ByteArrayConverter::class)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}