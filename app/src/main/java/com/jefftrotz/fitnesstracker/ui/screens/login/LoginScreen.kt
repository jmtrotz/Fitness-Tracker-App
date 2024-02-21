package com.jefftrotz.fitnesstracker.ui.screens.login

import android.widget.Toast
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.jefftrotz.fitnesstracker.R
import com.jefftrotz.fitnesstracker.model.intents.Cancel
import com.jefftrotz.fitnesstracker.navigation.Screens
import com.jefftrotz.fitnesstracker.ui.components.CommonTextField
import com.jefftrotz.fitnesstracker.ui.components.PasswordTextField
import com.jefftrotz.fitnesstracker.model.states.LoadingState
import com.jefftrotz.fitnesstracker.model.states.LoginState
import com.jefftrotz.fitnesstracker.model.intents.Login
import com.jefftrotz.fitnesstracker.model.intents.UpdateState
import com.jefftrotz.fitnesstracker.model.states.ErrorState
import com.jefftrotz.fitnesstracker.viewmodel.BaseViewModel

@Composable
fun LoginScreen(navController: NavController) {

    val viewModel: BaseViewModel by viewModels()
    val state = viewModel.getUIStateFlow().collectAsState()

    when (state.value) {
        is LoginState -> {
            val newState = state.value as LoginState
            if (newState.loginSuccessful) {
                navController.navigate(route = Screens.MainScreen.route) {
                    popUpTo(route = Screens.LoginScreen.route) {
                        inclusive = true
                    }
                }
            } else {
                renderScreen(viewModel = viewModel, state = newState)
            }
        }
        is LoadingState -> CircularProgressIndicator()
        is ErrorState -> {
            val error = state.value as ErrorState
            ShowToast(message = error.message)
        }
    }
}

@Composable
fun renderScreen(viewModel: BaseViewModel, state: LoginState) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoginForm(
                viewModel = viewModel,
                state = state,
                onEmailChanged = { newEmail ->
                    state.emailState.text = newEmail;
                    val intent = UpdateState(newState = state)
                    viewModel.setUserIntent(intent = intent)
                },
                onPasswordChanged = { newPassword ->
                    state.passwordState.text = newPassword;
                    val intent = UpdateState(newState = state)
                    viewModel.setUserIntent(intent = intent)
                }
            )
            ActionButtons(viewModel = viewModel)
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun LoginForm(
    viewModel: BaseViewModel,
    state: LoginState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit
) {
    val emailState = state.emailState
    val passwordState = state.passwordState

    Column {
        CommonTextField(
            value = emailState.text,
            onValueChange = onEmailChanged,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(4.dp),
            label = stringResource(id = R.string.placeholder_email),
            isError = emailState.isError,
            keyboardType = KeyboardType.Email,
            autoCorrect = false,
            imeAction = ImeAction.Next,
            errorMessage = "" // TODO: Fix
        )
        PasswordTextField(
            value = passwordState.text,
            onValueChange = onPasswordChanged,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(4.dp),
            label = stringResource(id = R.string.placeholder_password),
            onClick = {
                state.isPasswordVisible = !state.isPasswordVisible
                val intent = UpdateState(newState = state)
                viewModel.setUserIntent(intent = intent)
            },
            isPasswordVisible = state.isPasswordVisible,
            isError = passwordState.isError,
            errorMessage = passwordState.errorMessage
        )
    }
}

@Composable
private fun ActionButtons(viewModel: BaseViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(
            onClick = { viewModel.setUserIntent(Cancel()) },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            val buttonText = stringResource(id = R.string.button_label_cancel)
            Text(text = buttonText)
        }
        Button(
            onClick = { viewModel.setUserIntent(Login()) },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            val buttonText = stringResource(id = R.string.button_label_login)
            Text(text = buttonText)
        }
    }
}

@Composable
private fun ShowToast(message: String) {
    Toast.makeText(LocalContext.current, message, Toast.LENGTH_SHORT).show()
}