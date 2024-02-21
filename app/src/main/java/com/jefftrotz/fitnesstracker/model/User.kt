package com.jefftrotz.fitnesstracker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jefftrotz.fitnesstracker.model.Workout.Companion.workoutListFromCharList

@Entity(tableName = "users")
data class User(

    @PrimaryKey
    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "password")
    val password: ByteArray,

    @ColumnInfo(name = "password_salt")
    val passwordSalt: ByteArray,

    @ColumnInfo(name = "is_local_only")
    val isLocalOnly: Boolean,

    @ColumnInfo(name = "workouts")
    val workouts: List<Workout> = arrayListOf()
) {

    override fun toString(): String {
        return "email: $email, password: $password, passwordSalt: $passwordSalt, isLocalOnly: $isLocalOnly, workouts: $workouts"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other){
            return true
        }

        if (this.javaClass != other?.javaClass) {
            return false
        }

        other as User

        if (this.email != other.email) {
            return false
        }

        if (!this.password.contentEquals(other.password)) {
            return false
        }

        if (!this.passwordSalt.contentEquals(other.passwordSalt)) {
            return false
        }

        if (!this.isLocalOnly == other.isLocalOnly) {
            return false
        }

        return this.workouts == other.workouts
    }

    override fun hashCode(): Int {
        var result = email.hashCode()
        result = 31 * result + password.contentHashCode()
        result = 31 * result + passwordSalt.contentHashCode()
        result = 31 * result + isLocalOnly.hashCode()
        result = 31 * result + workouts.hashCode()
        return result
    }

    companion object {

        fun String.userFromString() : User {
            val email = this.substringAfter("email: ").substringBefore(",")
            val password = this.substringAfter("password: ").substringBefore(",")
            val passwordSalt = this.substringAfter("passwordSalt: ").substringBefore(",")
            val isLocalOnly = this.substringAfter("isLocalOnly: ").substringBefore(",")
            val workouts = this.substringAfter("workouts: ").substringBefore(",")

            return User(
                email = email,
                password = password.toByteArray(),
                passwordSalt = passwordSalt.toByteArray(),
                isLocalOnly = isLocalOnly.toBoolean(),
                workouts = workouts.toList().workoutListFromCharList()
            )
        }
    }
}