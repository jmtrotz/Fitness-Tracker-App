package com.jefftrotz.fitnesstracker.repository

import com.jefftrotz.fitnesstracker.data.local.user.UserDao
import com.jefftrotz.fitnesstracker.model.User
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {
    fun getAllUsers() = userDao.getAllUsers()
    suspend fun getUserByEmail(email: String) = userDao.getUserByEmail(email = email)
    suspend fun insertUser(user: User) = userDao.insertUser(user = user)
    suspend fun updateUser(user: User) =  userDao.updateUser(user = user)
    suspend fun deleteUser(user: User) = userDao.deleteUser(user = user)
    suspend fun deleteAllUsers() = userDao.deleteAllUsers()
}