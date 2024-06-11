package com.jefftrotz.fitnesstracker.repository

import com.jefftrotz.fitnesstracker.data.API
import com.jefftrotz.fitnesstracker.data.DAO
import com.jefftrotz.fitnesstracker.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class Repository @Inject constructor(private val api: API, private val dao: DAO) {

    // TODO: Finish networking
    suspend fun getRemoteUser(email: String) : User? {
        return api.getUser()
    }

    // TODO: Finish networking
    suspend fun insertRemoteUser(user: User) {
        api.postUser()
    }

    suspend fun getAllUsers() : Flow<List<User>> {
        return dao.getAllUsers()
    }

    suspend fun getUserByEmail(user: User) : Flow<User?> {
        return dao.getUserByEmail(user.email).flowOn(Dispatchers.IO)
    }

    suspend fun insertUser(user: User) {
        dao.insertUser(user = user)
    }

    suspend fun updateUser(user: User) {
        dao.updateUser(user = user)
    }

    suspend fun deleteUser(user: User) {
        dao.deleteUser(user = user)
    }

    suspend fun deleteAllUsers() {
        dao.deleteAllUsers()
    }
}