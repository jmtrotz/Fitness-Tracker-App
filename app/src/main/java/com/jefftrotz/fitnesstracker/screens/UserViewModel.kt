package com.jefftrotz.fitnesstracker.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.jefftrotz.fitnesstracker.model.User
import com.jefftrotz.fitnesstracker.repository.UserRepository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    private val _userList = MutableStateFlow<List<User>>(emptyList())
    val userList = _userList.asStateFlow()

    init {
        viewModelScope.launch(context = Dispatchers.IO) {
            repository.getAllUsers().distinctUntilChanged().collect { userList ->
                if (userList.isNullOrEmpty()) {
                    Log.e(TAG, "User list was null or empty")
                } else {
                    _userList.value = userList
                }
            }
        }
    }

    fun insertUser(user: User) = viewModelScope.launch {
        repository.insertUser(user = user)
    }

    fun updateUser(user: User) = viewModelScope.launch {
        repository.updateUser(user = user)
    }

    fun deleteUser(user: User) = viewModelScope.launch {
        repository.deleteUser(user = user)
    }

    fun deleteAllUsers() = viewModelScope.launch {
        repository.deleteAllUsers()
    }

    companion object {
        private const val TAG = "UserViewModel"
    }
}