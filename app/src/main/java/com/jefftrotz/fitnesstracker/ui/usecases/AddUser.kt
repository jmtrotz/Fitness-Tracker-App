package com.jefftrotz.fitnesstracker.ui.usecases

import android.util.Log
import com.jefftrotz.fitnesstracker.model.User
import com.jefftrotz.fitnesstracker.repository.Repository

class AddUser(private val repository: Repository) {

    suspend operator fun invoke(user: User, isLocalUser: Boolean) {
        if (user.email.isBlank()) {
            Log.d(TAG, "Email is blank")
            return
        }
        if (user.password.isEmpty()) {
            Log.d(TAG, "Password is empty")
            return
        }

        if (isLocalUser) {
            Log.d(TAG, "Inserting local user ${user.email}")
            repository.insertUser(user)
        } else {
            Log.d(TAG, "Inserting ${user.email}")
            repository.postUser(user)
        }
    }

    companion object {
        const val TAG = "AddUser"
    }
}