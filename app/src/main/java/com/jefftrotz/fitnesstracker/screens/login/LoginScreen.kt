package com.jefftrotz.fitnesstracker.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row

import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.jefftrotz.fitnesstracker.R
import com.jefftrotz.fitnesstracker.components.CommonTextField
import com.jefftrotz.fitnesstracker.components.ErrorText
import com.jefftrotz.fitnesstracker.components.PasswordTextField
import com.jefftrotz.fitnesstracker.navigation.FitnessTrackerScreens
import com.jefftrotz.fitnesstracker.util.login.LoginError
import com.jefftrotz.fitnesstracker.util.login.LoginUtils

@ExperimentalMaterial3Api
@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel) {
    var email by remember { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmation by rememberSaveable { mutableStateOf("") }
    var isNewUser by remember { mutableStateOf(false) }
    var isLocalAccount by remember { mutableStateOf(true) }
    var loginError by remember { mutableStateOf(LoginError.NONE) }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoginForm(
                email,
                password,
                confirmation,
                isNewUser,
                loginError,
                { email = it },
                { password = it },
                { confirmation = it }
            )
            if (isNewUser) {
                AccountTypeSwitch(isLocalAccount) {
                    isLocalAccount = !isLocalAccount
                }
            }
            ActionButtons(
                navController,
                viewModel,
                email,
                password,
                confirmation,
                isNewUser,
                isLocalAccount,
                { isNewUser = !isNewUser },
                { loginError = it },
            )
        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun LoginForm(
    email: String,
    password: String,
    confirmation: String,
    isNewUser: Boolean,
    loginError: LoginError,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onConfirmationChanged: (String) -> Unit
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isConfirmationVisible by remember { mutableStateOf(false) }

    CommonTextField(
        value = email,
        onValueChange = { onEmailChanged.invoke(it) },
        label = stringResource(R.string.placeholder_email),
        isError = loginError == LoginError.EMAIL_ERROR,
        keyboardType = KeyboardType.Email,
        autoCorrect = true,
        imeAction = ImeAction.Next
    )
    if (loginError == LoginError.EMAIL_ERROR || loginError == LoginError.ACCOUNT_EXISTS_ERROR) {
        ErrorText(
            text = if (email.isBlank()) stringResource(R.string.error_email_blank)
            else if (loginError == LoginError.ACCOUNT_EXISTS_ERROR)
                stringResource(R.string.error_account_exists)
            else stringResource(R.string.error_invalid_email)
        )
    }
    PasswordTextField(
        value = password,
        onValueChange = { onPasswordChanged.invoke(it) },
        label = stringResource(R.string.placeholder_password),
        onClick = { isPasswordVisible = !isPasswordVisible },
        isPasswordVisible = isPasswordVisible,
        isError = loginError == LoginError.PASSWORD_ERROR,
        isNewUser = isNewUser
    )
    if (loginError == LoginError.PASSWORD_ERROR) {
        ErrorText(
            text = if (password.isBlank()) stringResource(R.string.error_password_blank)
            else stringResource(R.string.error_invalid_password)
        )
    }
    if (isNewUser) {
        PasswordTextField(
            value = confirmation,
            onValueChange = { onConfirmationChanged.invoke(it) },
            label = stringResource(R.string.placeholder_confirm_password),
            onClick = { isConfirmationVisible = !isConfirmationVisible },
            isPasswordVisible = isConfirmationVisible,
            isError = loginError == LoginError.CONFIRMATION_ERROR,
            isNewUser = !isNewUser
        )
        if (loginError == LoginError.CONFIRMATION_ERROR) {
            ErrorText(
                text = if (confirmation.isBlank()) stringResource(R.string.error_confirmation_blank)
                else stringResource(R.string.error_passwords_do_not_match)
            )
        }
    }
}

@Composable
private fun AccountTypeSwitch(isLocalAccount: Boolean, onCheckedChanged: () -> Unit) {
    Row {
        Text(
            text = stringResource(R.string.toggle_label_off_the_grid_mode),
            modifier = Modifier.padding(top = 12.dp, end = 54.dp)
        )
        Switch(
            checked = isLocalAccount,
            onCheckedChange = {
                onCheckedChanged.invoke()
            },
            modifier = Modifier.padding(start = 54.dp)
        )
    }
    Text(
        text = if (isLocalAccount) stringResource(R.string.off_the_grid_mode_enabled_label)
        else stringResource(R.string.off_the_grid_mode_disabled_label),
        modifier = Modifier.fillMaxWidth(0.8f).padding(start = 8.dp, end = 8.dp)
    )
}

@ExperimentalMaterial3Api
@Composable
private fun ActionButtons(
    navController: NavController,
    viewModel: LoginViewModel,
    email: String,
    password: String,
    confirmation: String,
    isNewUser: Boolean,
    isLocalAccount: Boolean,
    onNewUserChanged: () -> Unit,
    onError: (LoginError) -> Unit
) {
    Row {
        Button(
            onClick = {
                onNewUserChanged.invoke()
            },
            modifier = Modifier.padding(top = 8.dp, end = 30.dp)
        ) {
            Text(
                text = if (isNewUser) stringResource(R.string.button_label_cancel)
                else stringResource(R.string.button_label_create_account)
            )
        }
        Button(
            onClick = {
                verifyInput(
                    viewModel,
                    email,
                    password,
                    confirmation,
                    isNewUser,
                    isLocalAccount,
                    { onError.invoke(it) }
                ) {
                    navController.navigate(FitnessTrackerScreens.MainScreen.name)
                }
            },
            modifier = Modifier.padding(start = 30.dp, top = 8.dp)
        ) {
            Text(
                text = if (isNewUser) stringResource(R.string.button_label_continue)
                else stringResource(R.string.button_label_login)
            )
        }
    }
}

private fun verifyInput(
    viewModel: LoginViewModel,
    email: String,
    password: String,
    confirmation: String,
    isNewUser: Boolean,
    isLocalAccount: Boolean,
    onError: (LoginError) -> Unit,
    onVerificationSuccess: () -> Unit
) {
    val loginUtils = LoginUtils()

    if (!loginUtils.isEmailAddressValid(email)) {
        onError.invoke(LoginError.EMAIL_ERROR)
        return
    }
    if (!loginUtils.isPasswordValid(password)) {
        onError.invoke(LoginError.PASSWORD_ERROR)
        return
    }
    if (isNewUser && !loginUtils.isConfirmationValid(password, confirmation)) {
        onError.invoke(LoginError.CONFIRMATION_ERROR)
        return
    }

    val user = viewModel.getUserByEmail(email)

    if (user != null && isNewUser) {
        onError.invoke(LoginError.ACCOUNT_EXISTS_ERROR)
    } else if (user == null && isNewUser) {
        val salt = loginUtils.generateSalt()
        val hashedPassword = loginUtils.hash(password, salt)
        viewModel.addUser(email, hashedPassword, salt, isLocalAccount)
        onVerificationSuccess.invoke()
    } else if (user != null) {
        if (loginUtils.isCorrectPassword(password, user.password, user.passwordSalt)) {
            onVerificationSuccess.invoke()
        } else {
            onError.invoke(LoginError.INCORRECT_CREDENTIALS_ERROR)
        }
    }
}