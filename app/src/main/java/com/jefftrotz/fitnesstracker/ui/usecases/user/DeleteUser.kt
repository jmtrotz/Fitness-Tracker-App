package com.jefftrotz.fitnesstracker.ui.usecases.user

import android.util.Log
import com.jefftrotz.fitnesstracker.model.User
import com.jefftrotz.fitnesstracker.repository.Repository

class DeleteUser(private val repository: Repository) {

    suspend operator fun invoke(user: User) {
        Log.d(TAG, "Deleting ${user.email}")
        repository.deleteUser(user)
    }

    companion object {
        const val TAG = "DeleteUser"
    }
}