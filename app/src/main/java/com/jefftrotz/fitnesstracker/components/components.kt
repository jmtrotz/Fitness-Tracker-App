package com.jefftrotz.fitnesstracker.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.jefftrotz.fitnesstracker.R

@ExperimentalMaterial3Api
@Composable
fun TopBar(title: String,
           icon: ImageVector,
           contentDescription: String,
           onClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = { Text(text = title) },
        actions = {
            IconButton(onClick = onClick) {
                Icon(imageVector = icon, contentDescription = contentDescription)
            }
        }
    )
}

@Composable
fun AddButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        shape = RoundedCornerShape(size = 50.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(R.string.content_description_add_button_icon)
        )
    }
}

@Composable
fun ErrorText(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(start = 8.dp, end = 8.dp),
        color = MaterialTheme.colorScheme.error
    )
}

@ExperimentalMaterial3Api
@Composable
fun CommonTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    autoCorrect: Boolean = false,
    imeAction: ImeAction = ImeAction.Default,
    onAction: KeyboardActions = KeyboardActions.Default,
    errorMessage: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(4.dp),
        label = { Text(text = label) },
        isError = isError,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            autoCorrect = autoCorrect,
            imeAction = imeAction
        ),
        keyboardActions = onAction,
        singleLine = true,
        maxLines = 1,
        shape = RoundedCornerShape(16.dp)
    )

    if (isError) {
        ErrorText(text = errorMessage)
    }
}

@ExperimentalMaterial3Api
@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    onClick: () -> Unit,
    isPasswordVisible: Boolean,
    isError: Boolean,
    isNewUser: Boolean,
    errorMessage: String
) {
    val visualTransformation = if (isPasswordVisible) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }

    val imeAction = if (isNewUser) {
        ImeAction.Next
    } else {
        ImeAction.Done
    }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(4.dp),
        label = { Text(text = label) },
        trailingIcon = { PasswordVisibilityButton(isPasswordVisible) { onClick() } },
        isError = isError,
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            autoCorrect = false,
            imeAction = imeAction
        ),
        singleLine = true,
        maxLines = 1,
        shape = RoundedCornerShape(16.dp)
    )

    if (isError) {
        ErrorText(text = errorMessage)
    }
}

@Composable
fun PasswordVisibilityButton(isPasswordVisible: Boolean, onClick: () -> Unit) {
    val icon = if (isPasswordVisible) {
        Icons.Filled.Visibility
    } else {
        Icons.Filled.VisibilityOff
    }

    IconButton(onClick = onClick) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(R.string.content_description_show_password_icon)
        )
    }
}