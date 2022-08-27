package com.jefftrotz.fitnesstracker.util.login

import android.util.Log
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.spec.InvalidKeySpecException
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

class LoginUtils {
    fun isEmailAddressValid(email: String): Boolean {
        if (email.trim().isBlank()) return false
        if (!email.trim().matches(EMAIL_REGEX)) return false
        return true
    }

    fun isPasswordValid(password: String): Boolean {
        if (password.trim().isBlank()) return false
        if (!password.trim().matches(PASSWORD_REGEX)) return false
        return true
    }

    fun isConfirmationValid(password: String, confirmation: String): Boolean {
        if (confirmation.trim().isBlank()) return false
        if (password.trim() != confirmation.trim()) return false
        return true
    }

    fun isCorrectPassword(password: String, salt: ByteArray, expectedHash: ByteArray): Boolean {
        val passwordHash = hash(password, salt)
        if (passwordHash.size != expectedHash.size) return false
        return passwordHash.indices.all { passwordHash[it] == expectedHash[it] }
    }

    fun hash(password: String, salt: ByteArray): ByteArray {
        val keySpec = PBEKeySpec(password.toCharArray(), salt, 1000, 256)
        try {
            val keyFactory = SecretKeyFactory.getInstance(HASHING_ALGORITHM)
            return keyFactory.generateSecret(keySpec).encoded
        } catch (exception: NoSuchAlgorithmException) {
            Log.e(TAG, "Error while hashing password. NoSuchAlgorithmException.")
            throw AssertionError("Error while hashing password: " + exception.message)
        } catch (exception: InvalidKeySpecException) {
            Log.e(TAG, "Error while hashing password. InvalidKeySpecException.")
            throw AssertionError("Error while hashing password: " + exception.message)
        } finally {
            keySpec.clearPassword()
        }
    }

    fun generateSalt(): ByteArray {
        val salt = ByteArray(16)
        SecureRandom().nextBytes(salt)
        return salt
    }

    companion object {
        private const val TAG = "LoginUtils"
        private const val HASHING_ALGORITHM = "PBKDF2WithHmacSHA1"
        private val EMAIL_REGEX = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        private val PASSWORD_REGEX = Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%!\\-_?&])(?=\\S+\$).{8,24}")
    }
}