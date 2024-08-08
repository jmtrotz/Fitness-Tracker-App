package com.jefftrotz.fitnesstracker.repository

import com.jefftrotz.fitnesstracker.model.User
import kotlinx.coroutines.flow.Flow

interface Repository {

    // TODO: Finish networking
    suspend fun getRemoteUser(email: String): User?

    // TODO: Finish networking
    suspend fun insertRemoteUser(user: User)

    suspend fun getAllUsers(): Flow<List<User>>

    suspend fun getUserByEmail(email: String): Flow<User?>

    suspend fun insertUser(user: User)

    suspend fun updateUser(user: User)

    suspend fun deleteUser(user: User)

    suspend fun deleteAllUsers()
}