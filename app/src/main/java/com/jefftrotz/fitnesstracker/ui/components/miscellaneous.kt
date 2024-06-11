package com.jefftrotz.fitnesstracker.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jefftrotz.fitnesstracker.R

@ExperimentalMaterial3Api
@Composable
fun TopBar(
    title: String = "",
    isMainScreen: Boolean = false,
    onClick: () -> Unit = {},
    actions: @Composable () -> Unit
) {
    if (isMainScreen) {
        CenterAlignedTopAppBar(
            title = {
                Text(text = title)
            },
            actions = {
                actions()
            }
        )
    } else {
        CenterAlignedTopAppBar(
            title = {
                Text(text = title)
            },
            navigationIcon = {
                IconButton(onClick = onClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.content_description_back_button_icon)
                    )
                }
            },
            actions = {
                actions()
            }
        )
    }
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
fun ShowDialog(text: String, onDismiss: () -> Unit, onConfirm: () -> Unit) {
    AlertDialog(
        onDismissRequest = {
            onDismiss()
        },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text(text = stringResource(id = R.string.dialog_confirm_button_label))
            }
        },
        dismissButton = {
            Button(onClick = onDismiss){
                Text(text = stringResource(id = R.string.dialog_dismiss_button_label))
            }
        },
        text = {
            Text(text = text)
        },
        shape = RoundedCornerShape(16.dp)
    )
}