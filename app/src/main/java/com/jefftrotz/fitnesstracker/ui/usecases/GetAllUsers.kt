package com.jefftrotz.fitnesstracker.ui.usecases

import android.util.Log
import com.jefftrotz.fitnesstracker.model.User
import com.jefftrotz.fitnesstracker.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetAllUsers(private val repository: Repository) {

    suspend operator fun invoke(): Flow<List<User>> {
        Log.d(TAG, "Getting all users")
        return repository.getAllUsers()
    }

    companion object {
        const val TAG = "GetAllUsers"
    }
}