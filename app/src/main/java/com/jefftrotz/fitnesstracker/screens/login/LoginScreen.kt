package com.jefftrotz.fitnesstracker.screens.login

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.jefftrotz.fitnesstracker.R
import com.jefftrotz.fitnesstracker.components.CommonTextField
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
    var error by remember { mutableStateOf(LoginError.NONE) }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoginForm(
                email = email,
                password = password,
                confirmation = confirmation,
                isNewUser = isNewUser,
                error = error,
                onEmailChanged = {
                    email = it
                    error = LoginError.NONE
                },
                onPasswordChanged = {
                    password = it
                    error = LoginError.NONE
                },
                onConfirmationChanged = {
                    confirmation = it
                    error = LoginError.NONE
                }
            )

            if (isNewUser) {
                AccountTypeSwitch(isLocalAccount) {
                    isLocalAccount = !isLocalAccount
                }
            }

            ActionButtons(
                navController = navController,
                viewModel = viewModel,
                email = email,
                password = password,
                confirmation = confirmation,
                isNewUser = isNewUser,
                isLocalAccount = isLocalAccount,
                onClick = {
                    isNewUser = !isNewUser
                    if (confirmation.isNotBlank()) confirmation = ""
                },
                onError = { error = it },
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
    error: LoginError,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onConfirmationChanged: (String) -> Unit
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isConfirmationVisible by remember { mutableStateOf(false) }

    CommonTextField(
        value = email,
        onValueChange = onEmailChanged,
        label = stringResource(R.string.placeholder_email),
        isError = error == LoginError.EMAIL_ERROR,
        keyboardType = KeyboardType.Email,
        autoCorrect = false,
        imeAction = ImeAction.Next,
        resourceId = getResourceId(error = error, email = email)
    )

    PasswordTextField(
        value = password,
        onValueChange = onPasswordChanged,
        label = stringResource(R.string.placeholder_password),
        onClick = { isPasswordVisible = !isPasswordVisible },
        isPasswordVisible = isPasswordVisible,
        isError = error == LoginError.PASSWORD_ERROR,
        isNewUser = isNewUser,
        resourceId = getResourceId(error = error, password = password)
    )

    if (isNewUser) {
        PasswordTextField(
            value = confirmation,
            onValueChange = onConfirmationChanged,
            label = stringResource(R.string.placeholder_confirm_password),
            onClick = { isConfirmationVisible = !isConfirmationVisible },
            isPasswordVisible = isConfirmationVisible,
            isError = error == LoginError.CONFIRMATION_ERROR,
            isNewUser = !isNewUser,
            resourceId = getResourceId(error = error, confirmation = confirmation)
        )
    }

    if (error == LoginError.INCORRECT_CREDENTIALS_ERROR) {
        Toast.makeText(
            LocalContext.current,
            stringResource(R.string.error_incorrect_credentials),
            Toast.LENGTH_LONG
        ).show()
    }
}

@Composable
private fun AccountTypeSwitch(isLocalAccount: Boolean, onCheckedChanged: (Boolean) -> Unit) {
    Row {
        Text(
            text = stringResource(R.string.toggle_label_off_the_grid_mode),
            modifier = Modifier.padding(top = 12.dp, end = 54.dp)
        )

        Switch(
            checked = isLocalAccount,
            onCheckedChange = onCheckedChanged,
            modifier = Modifier.padding(start = 54.dp)
        )
    }

    val switchLabel = if (isLocalAccount) {
        stringResource(R.string.off_the_grid_mode_enabled_label)
    } else {
        stringResource(R.string.off_the_grid_mode_disabled_label)
    }

    Text(text = switchLabel,
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(start = 8.dp, end = 8.dp)
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
    onClick: () -> Unit,
    onError: (LoginError) -> Unit
) {
    Row {
        Button(
            onClick = onClick,
            modifier = Modifier.padding(top = 8.dp, end = 30.dp)
        ) {
            val buttonText = if (isNewUser) {
                stringResource(R.string.button_label_cancel)
            } else {
                stringResource(R.string.button_label_create_account)
            }
            Text(text = buttonText)
        }

        Button(
            onClick = {
                verifyInput(
                    viewModel = viewModel,
                    email = email,
                    password = password,
                    confirmation = confirmation,
                    isNewUser = isNewUser,
                    isLocalAccount = isLocalAccount,
                    onError = onError
                ) {
                    navController.navigate(FitnessTrackerScreens.MainScreen.name) {
                        popUpTo(FitnessTrackerScreens.LoginScreen.name) { inclusive = true }
                    }
                }
            },
            modifier = Modifier.padding(start = 30.dp, top = 8.dp)
        ) {
            val buttonText = if (isNewUser) {
                stringResource(R.string.button_label_continue)
            } else {
                stringResource(R.string.button_label_login)
            }
            Text(text = buttonText)
        }
    }
}

private fun getResourceId(
    error: LoginError,
    email: String = "",
    password: String = "",
    confirmation: String = ""
): Int {
    when (error) {
        LoginError.EMAIL_ERROR -> {
            return if (email.trim().isBlank()) {
                R.string.error_email_blank
            } else {
                R.string.error_invalid_email
            }
        }
        LoginError.PASSWORD_ERROR -> {
            return if (password.trim().isBlank()) {
                R.string.error_password_blank
            } else {
                R.string.error_invalid_password
            }
        }
        LoginError.CONFIRMATION_ERROR -> {
            return if (confirmation.trim().isBlank()) {
                R.string.error_confirmation_blank
            } else {
                R.string.error_passwords_do_not_match
            }
        }
        LoginError.ACCOUNT_EXISTS_ERROR -> return R.string.error_account_exists
        else -> return -1
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
        onError(LoginError.EMAIL_ERROR)
        return
    }
    if (!loginUtils.isPasswordValid(password)) {
        onError(LoginError.PASSWORD_ERROR)
        return
    }
    if (isNewUser && !loginUtils.isConfirmationValid(password, confirmation)) {
        onError(LoginError.CONFIRMATION_ERROR)
        return
    }

    val user = viewModel.getUserByEmail(email)

    if (user != null && isNewUser) {
        onError(LoginError.ACCOUNT_EXISTS_ERROR)
    } else if (user == null && isNewUser) {
        val salt = loginUtils.generateSalt()
        val hashedPassword = loginUtils.hash(password, salt)
        viewModel.addUser(email, hashedPassword, salt, isLocalAccount)
        onVerificationSuccess()
    } else if (user != null) {
        if (loginUtils.isCorrectPassword(password, user.password, user.passwordSalt)) {
            onVerificationSuccess()
        } else {
            onError(LoginError.INCORRECT_CREDENTIALS_ERROR)
        }
    }
}