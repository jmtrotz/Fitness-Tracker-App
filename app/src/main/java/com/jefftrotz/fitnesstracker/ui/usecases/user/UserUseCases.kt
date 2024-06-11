package com.jefftrotz.fitnesstracker.ui.usecases.user

data class UserUseCases (
    val getAllUsers: GetAllUsers,
    val getUser: GetUser,
    val addUser: AddUser,
    val updateUser: UpdateUser,
    val deleteUser: DeleteUser
)