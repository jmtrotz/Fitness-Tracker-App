package com.jefftrotz.fitnesstracker.model.states

class LoginState(
    val emailState: TextFieldState = TextFieldState(),
    val passwordState: TextFieldState = TextFieldState(),
    var isPasswordVisible: Boolean = false,
    var loginSuccessful: Boolean = false
) : UIState()