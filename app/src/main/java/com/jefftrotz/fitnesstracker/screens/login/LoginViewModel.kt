package com.jefftrotz.fitnesstracker.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.jefftrotz.fitnesstracker.model.User
import com.jefftrotz.fitnesstracker.repository.UserRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    var user: User? = null

    fun getUserByEmail(email: String) {
        viewModelScope.launch {
            user = repository.getUserByEmail(email)
        }
    }

    fun addUser(
        email: String,
        password: ByteArray,
        passwordSalt: ByteArray,
        localAccount: Boolean
    ) {
        val user = User(email, password, passwordSalt, localAccount)
        if (localAccount) {
            viewModelScope.launch {
                repository.insertUser(user)
            }
        } else {
            viewModelScope.launch {
                repository.postUser(user)
            }
        }
    }
}