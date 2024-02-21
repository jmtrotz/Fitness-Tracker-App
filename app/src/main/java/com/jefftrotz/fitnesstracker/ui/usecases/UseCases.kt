package com.jefftrotz.fitnesstracker.ui.usecases

data class UseCases (
    val getAllUsers: GetAllUsers,
    val getUser: GetUser,
    val addUser: AddUser,
    val updateUser: UpdateUser,
    val deleteUser: DeleteUser
)