package com.jefftrotz.fitnesstracker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jefftrotz.fitnesstracker.model.Workout.Companion.workoutListFromCharList

/**
 * Object to represent a user of the application.
 * @param email String representing the user's email.
 * @param password Byte array representing the user's password.
 * @param passwordSalt Byte array representing the salt for the user's password.
 * @param isLocalOnly Boolean representing if the user's data is only stored locally
 * on the device, or if their data is stored locally and synced with the server.
 * @param workouts List of [Workout]s representing workouts completed by the user.
 * @see Workout
 */
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

    /**
     * Converts a [User] object to a String.
     * @return Returns a String representing the [User] object.
     */
    override fun toString() : String {
        return "email: $email, password: $password, passwordSalt: $passwordSalt, isLocalOnly: $isLocalOnly, workouts: $workouts"
    }

    /**
     * Checks if a [User] object is equal to another [User] object.
     * @return Returns a boolean representing whether the [User]
     * objects are equal or not.
     */
    override fun equals(other: Any?) : Boolean {
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

        if (this.isLocalOnly != other.isLocalOnly) {
            return false
        }

        if (!this.workouts.containsAll(other.workouts)) {
            return false
        }

        return this.workouts == other.workouts
    }

    /**
     * Calculates the hashcode for a [User] object.
     * @return Int representing the hash code.
     */
    override fun hashCode() : Int {
        var result = email.hashCode()
        result = 31 * result + password.contentHashCode()
        result = 31 * result + passwordSalt.contentHashCode()
        result = 31 * result + isLocalOnly.hashCode()
        result = 31 * result + workouts.hashCode()
        return result
    }

    companion object {

        /**
         * Converts a String to a [User].
         * @return Returns a [User] object representing the String.
         */
        fun String.userFromString() : User {
            val passwordSalt = this.substringAfter("passwordSalt: ").substringBefore(",")
            val isLocalOnly = this.substringAfter("isLocalOnly: ").substringBefore(",")
            val workouts = this.substringAfter("workouts: ").substringBefore(",")
            val password = this.substringAfter("password: ").substringBefore(",")
            val email = this.substringAfter("email: ").substringBefore(",")

            return User(
                workouts = workouts.toList().workoutListFromCharList(),
                passwordSalt = passwordSalt.toByteArray(),
                isLocalOnly = isLocalOnly.toBoolean(),
                password = password.toByteArray(),
                email = email
            )
        }
    }
}