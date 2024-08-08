package com.jefftrotz.fitnesstracker.ui.usecases.user

data class UserUseCases (
    val getAllUsers: GetAllUsers,
    val updateUser: UpdateUser,
    val deleteUser: DeleteUser,
    val getUser: GetUser,
    val addUser: AddUser
)