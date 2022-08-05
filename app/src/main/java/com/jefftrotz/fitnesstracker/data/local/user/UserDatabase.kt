package com.jefftrotz.fitnesstracker.data.local.user

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jefftrotz.fitnesstracker.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}