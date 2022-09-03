package com.jefftrotz.fitnesstracker.util.login

import android.util.Log
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.spec.InvalidKeySpecException
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

class LoginUtils {
    private val emailRegex = Regex(EMAIL_PATTERN)
    private val passwordRegex = Regex(PASSWORD_PATTERN)

    fun isEmailAddressValid(email: String): Boolean {
        if (email.trim().isBlank()) {
            return false
        }
        if (!email.trim().matches(emailRegex)) {
            return false
        }
        return true
    }

    fun isPasswordValid(password: String): Boolean {
        if (password.trim().isBlank()) {
            return false
        }
        if (!password.trim().matches(passwordRegex)) {
            return false
        }
        return true
    }

    fun isConfirmationValid(password: String, confirmation: String): Boolean {
        if (!isPasswordValid(password)) {
            return false
        }
        if (confirmation.trim().isBlank()) {
            return false
        }
        if (password.trim() != confirmation.trim()) {
            return false
        }
        return true
    }

    fun isCorrectPassword(password: String, expectedHash: ByteArray, salt: ByteArray): Boolean {
        val passwordHash = hash(password, salt)
        if (passwordHash.size != expectedHash.size){
            return false
        }
        return passwordHash.indices.all { index ->
            passwordHash[index] == expectedHash[index]
        }
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
        private const val HASHING_ALGORITHM = "PBKDF2WithHmacSHA512"
        private const val EMAIL_PATTERN ="^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        private const val PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%!\\-_?&])(?=\\S+\$).{8,24}"
    }
}