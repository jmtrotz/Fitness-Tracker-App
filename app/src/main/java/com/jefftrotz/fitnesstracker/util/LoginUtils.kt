package com.jefftrotz.fitnesstracker.util

import android.util.Log
import java.security.SecureRandom
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

object LoginUtils {

    private const val TAG = "LoginUtils"
    private const val HASHING_ALGORITHM = "PBKDF2WithHmacSHA512"
    private const val EMAIL_PATTERN ="^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
    private const val PASSWORD_PATTERN =
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%!\\-_?&])(?=\\S+\$).{8,24}"

    private val emailRegex = Regex(EMAIL_PATTERN)
    private val passwordRegex = Regex(PASSWORD_PATTERN)

    fun isEmailAddressValid(email: String): Boolean {
        return email.trim().matches(emailRegex)
    }

    fun isPasswordValid(password: String): Boolean {
        return password.trim().matches(passwordRegex)
    }

    fun isConfirmationValid(password: String, confirmation: String): Boolean {
        return password.trim() == confirmation.trim()
    }

    fun verifyPassword(password: String, expectedHash: ByteArray, salt: ByteArray): Boolean {
        try {
            val keySpec = PBEKeySpec(password.toCharArray(), salt, 1000, 256)
            val keyFactory = SecretKeyFactory.getInstance(HASHING_ALGORITHM)
            val passwordHash = keyFactory.generateSecret(keySpec).encoded

            if (passwordHash.size != expectedHash.size) {
                return false
            }
            return passwordHash.indices.all { index ->
                passwordHash[index] == expectedHash[index]
            }
        } catch (exception: Exception) {
            Log.e(TAG, "Failed to hash password", exception)
            return false
        }
    }

    fun generatePasswordSalt(): ByteArray {
        val salt = ByteArray(16)
        SecureRandom().nextBytes(salt)
        return salt
    }
}