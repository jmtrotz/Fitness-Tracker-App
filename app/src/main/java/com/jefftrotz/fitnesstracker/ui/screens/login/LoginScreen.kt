package com.jefftrotz.fitnesstracker.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row

import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

import androidx.hilt.navigation.compose.hiltViewModel
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
import com.jefftrotz.fitnesstracker.viewmodel.BaseViewModel

@Composable
fun LoginScreen(navController: NavController) {

    val snackbarState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val viewModel = hiltViewModel<LoginViewModel>()
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
                RenderScreen(viewModel = viewModel, state = newState)
            }
        }
        is LoadingState -> CircularProgressIndicator()
    }

    LaunchedEffect(key1 = coroutineScope.coroutineContext) {
        viewModel.getSideEffectFlow().collect { message ->
            snackbarState.showSnackbar(message = message)
        }
    }
}

@Composable
fun RenderScreen(viewModel: BaseViewModel, state: LoginState) {
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
            label = emailState.hint,
            isError = emailState.isError,
            keyboardType = KeyboardType.Email,
            autoCorrect = false,
            imeAction = ImeAction.Next,
            errorMessage = emailState.errorMessage
        )
        PasswordTextField(
            value = passwordState.text,
            onValueChange = onPasswordChanged,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(4.dp),
            label = passwordState.hint,
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