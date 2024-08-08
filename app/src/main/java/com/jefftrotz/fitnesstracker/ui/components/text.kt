package com.jefftrotz.fitnesstracker.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.jefftrotz.fitnesstracker.R

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

@Composable
fun CommonTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String,
    isError: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    autoCorrect: Boolean = false,
    imeAction: ImeAction = ImeAction.Default,
    onAction: KeyboardActions = KeyboardActions.Default,
    errorMessage: String = ""
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = {
            Text(text = label)
        },
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

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String,
    onClick: () -> Unit,
    isPasswordVisible: Boolean,
    isError: Boolean,
    errorMessage: String
) {
    val visualTransformation = if (isPasswordVisible) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = {
            Text(text = label)
        },
        trailingIcon = {
            PasswordVisibilityButton(isPasswordVisible) {
                onClick()
            }
        },
        isError = isError,
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            autoCorrect = false,
            imeAction = ImeAction.Done
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
        Icons.Filled.VisibilityOff
    } else {
        Icons.Filled.Visibility
    }

    IconButton(onClick = onClick) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(R.string.content_description_show_password_icon)
        )
    }
}