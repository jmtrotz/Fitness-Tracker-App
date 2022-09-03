package com.jefftrotz.fitnesstracker.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "user_email")
    val email: String,

    @NonNull
    @ColumnInfo(name = "user_password")
    val password: ByteArray,

    @NonNull
    @ColumnInfo(name = "user_password_salt")
    val passwordSalt: ByteArray,

    @NonNull
    @ColumnInfo(name = "user_local_account")
    val localAccount: Boolean
) {
    override fun equals(other: Any?): Boolean {
        if (this === other){
            return true
        }
        if (javaClass != other?.javaClass){
            return false
        }

        other as User

        if (email != other.email){
            return false
        }
        if (!password.contentEquals(other.password)){
            return false
        }
        if (!passwordSalt.contentEquals(other.passwordSalt)){
            return false
        }
        if (localAccount != other.localAccount){
            return false
        }
        return true
    }

    override fun hashCode(): Int {
        var result = email.hashCode()
        result = 31 * result + password.contentHashCode()
        result = 31 * result + passwordSalt.contentHashCode()
        result = 31 * result + localAccount.hashCode()
        return result
    }
}