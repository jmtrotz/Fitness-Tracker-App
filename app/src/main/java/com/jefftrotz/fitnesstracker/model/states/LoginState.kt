package com.jefftrotz.fitnesstracker.model.states

/**
 * [UIState] for the login screen.
 * @param passwordState [TextFieldState] representing the state of the password text field.
 * @param emailState [TextFieldState] representing the state of the email text field.
 * @param isPasswordVisible Boolean representing whether the password is visible or not.
 * @param isLoginSuccessful Boolean representing whether the login was successful or not.
 * @see TextFieldState
 * @see UIState
 */
data class LoginState(
    val passwordState: TextFieldState = TextFieldState(),
    val emailState: TextFieldState = TextFieldState(),
    var isPasswordVisible: Boolean = false,
    var isLoginSuccessful: Boolean = false
): UIState()