package com.jefftrotz.fitnesstracker.repository

import com.jefftrotz.fitnesstracker.data.local.user.UserDao
import com.jefftrotz.fitnesstracker.data.remote.UserApi
import com.jefftrotz.fitnesstracker.model.User

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao,
    private val userApi: UserApi
) {
    fun getAllUsers() = userDao.getAllUsers().flowOn(Dispatchers.IO)
    suspend fun getUserByEmail(email: String) = userDao.getUserByEmail(email)
    suspend fun insertUser(user: User) = userDao.insertUser(user)
    suspend fun updateUser(user: User) =  userDao.updateUser(user)
    suspend fun deleteUser(user: User) = userDao.deleteUser(user)
    suspend fun deleteAllUsers() = userDao.deleteAllUsers()

    suspend fun getUser(email: String) = userApi.getUser()
    suspend fun postUser(user: User) = userApi.postUser()
}