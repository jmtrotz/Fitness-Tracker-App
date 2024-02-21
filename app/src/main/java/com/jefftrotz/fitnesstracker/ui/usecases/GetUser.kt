package com.jefftrotz.fitnesstracker.ui.usecases

import android.util.Log
import com.jefftrotz.fitnesstracker.model.User
import com.jefftrotz.fitnesstracker.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetUser(private val repository: Repository) {

    suspend operator fun invoke(email: String): Flow<User?> {
        Log.d(TAG, "Getting $email")
        return repository.getUserByEmail(email)
    }

    companion object {
        const val TAG = "GetUser"
    }
}