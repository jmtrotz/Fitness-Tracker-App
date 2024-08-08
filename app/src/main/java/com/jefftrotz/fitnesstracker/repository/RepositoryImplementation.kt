package com.jefftrotz.fitnesstracker.repository

import com.jefftrotz.fitnesstracker.data.API
import com.jefftrotz.fitnesstracker.data.DAO
import com.jefftrotz.fitnesstracker.model.User

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RepositoryImplementation @Inject constructor(
    private val api: API,
    private val dao: DAO
): Repository {

    // TODO: Finish networking
    override suspend fun getRemoteUser(email: String): User? {
        return api.getUser()
    }

    // TODO: Finish networking
    override suspend fun insertRemoteUser(user: User) {
        api.postUser()
    }

    override suspend fun getAllUsers(): Flow<List<User>> {
        return dao.getAllUsers()
    }

    override suspend fun getUserByEmail(email: String): Flow<User?> {
        return dao.getUserByEmail(email = email).flowOn(Dispatchers.IO)
    }

    override suspend fun insertUser(user: User) {
        dao.insertUser(user = user)
    }

    override suspend fun updateUser(user: User) {
        dao.updateUser(user = user)
    }

    override suspend fun deleteUser(user: User) {
        dao.deleteUser(user = user)
    }

    override suspend fun deleteAllUsers() {
        dao.deleteAllUsers()
    }
}