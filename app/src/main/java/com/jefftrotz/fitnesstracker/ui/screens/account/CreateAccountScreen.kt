package com.jefftrotz.fitnesstracker.ui.screens.account

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jefftrotz.fitnesstracker.R
import kotlinx.coroutines.launch

@Composable
fun CreateAccount(navController: NavController) {

    val snackbarState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val viewModel = hiltViewModel<AccountViewModel>()

    coroutineScope.launch {
        viewModel.getSideEffectFlow().collect { message ->
            snackbarState.showSnackbar(message = message)
        }
    }
}

@Composable
private fun AccountTypeSwitch(isLocalAccount: Boolean, onCheckedChanged: (Boolean) -> Unit) {
    Row {
        Text(
            text = stringResource(id = R.string.toggle_label_off_the_grid_mode),
            modifier = Modifier.padding(top = 12.dp, end = 54.dp)
        )

        Switch(
            checked = isLocalAccount,
            onCheckedChange = onCheckedChanged,
            modifier = Modifier.padding(start = 54.dp)
        )
    }

    val switchLabel = if (isLocalAccount) {
        stringResource(id = R.string.off_the_grid_mode_enabled_label)
    } else {
        stringResource(id = R.string.off_the_grid_mode_disabled_label)
    }

    Text(
        text = switchLabel,
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(start = 8.dp, end = 8.dp)
    )
}