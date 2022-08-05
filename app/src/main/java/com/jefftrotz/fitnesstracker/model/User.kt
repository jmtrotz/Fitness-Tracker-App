package com.jefftrotz.fitnesstracker.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class User(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "user_email")
    val email: String,

    @NonNull
    @ColumnInfo(name = "user_password")
    val password: String,

    @NonNull
    @ColumnInfo(name = "user_name")
    val name: String
)