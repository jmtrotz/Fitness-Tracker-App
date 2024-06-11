package com.jefftrotz.fitnesstracker.ui.usecases.user

import android.util.Log
import com.jefftrotz.fitnesstracker.model.User
import com.jefftrotz.fitnesstracker.repository.Repository

class UpdateUser(private val repository: Repository) {

    suspend operator fun invoke(user: User) {
        Log.d(TAG, "Updating ${user.email}")
        repository.updateUser(user)
    }

    companion object {
        const val TAG = "UpdateUser"
    }
}