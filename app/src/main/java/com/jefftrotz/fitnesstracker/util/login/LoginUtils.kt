package com.jefftrotz.fitnesstracker.util.login

import android.util.Log
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.spec.InvalidKeySpecException
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
            Log.e(TAG, "Error while hashing password", exception)
            throw AssertionError("Error while hashing password: " + exception.message)
        } catch (exception: InvalidKeySpecException) {
            Log.e(TAG, "Error while hashing password", exception)
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
}