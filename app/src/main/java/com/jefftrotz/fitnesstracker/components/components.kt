package com.jefftrotz.fitnesstracker.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jefftrotz.fitnesstracker.R

@ExperimentalMaterial3Api
@Composable
fun TopBar(title: String,
           isMainScreen: Boolean = false,
           onClick: () -> Unit = {},
           dropDownMenuItems: @Composable () -> Unit
) {
    var isMenuExpanded by remember {
        mutableStateOf(false)
    }

    if (isMainScreen) {
        CenterAlignedTopAppBar(
            title = { Text(text = title) },
            actions = {
                IconButton(
                    onClick = {
                        isMenuExpanded = !isMenuExpanded
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = stringResource(R.string.content_description_menu_button_icon)
                    )
                    DropdownMenu(
                        expanded = isMenuExpanded,
                        onDismissRequest = {
                            isMenuExpanded = !isMenuExpanded
                        }
                    ) {
                        dropDownMenuItems()
                    }
                }
            }
        )
    } else {
        CenterAlignedTopAppBar(
            title = { Text(text = title) },
            navigationIcon = {
                IconButton(onClick = onClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(R.string.content_description_back_button_icon)
                    )
                }
            },
            actions = {
                IconButton(
                    onClick = {
                        isMenuExpanded = !isMenuExpanded
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = stringResource(R.string.content_description_menu_button_icon)
                    )
                    DropdownMenu(
                        expanded = isMenuExpanded,
                        onDismissRequest = {
                            isMenuExpanded = !isMenuExpanded
                        }
                    ) {
                        dropDownMenuItems()
                    }
                }
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
    modifier: Modifier = Modifier,
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
        modifier = modifier,
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

@Composable
fun <T> ItemList(list: List<T>, content: @Composable (T) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(items = list) { item ->
            content(item)
        }
    }
}

@Composable
fun ListItem(
    itemName: String,
    itemDetails: String,
    expandedDetails: Map<String, Any> = HashMap(),
    dropDownMenuItems: @Composable () -> Unit
) {
    var isCardExpanded by remember {
        mutableStateOf(true)
    }

    Card(modifier = Modifier.padding(8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.padding(start = 8.dp, top = 9.dp, bottom = 8.dp)) {
                Text(
                    text = itemName,
                    fontSize = 20.sp
                )

                Text(
                    text = itemDetails,
                    fontSize = 16.sp
                )

                if (isCardExpanded && expandedDetails.isNotEmpty()) {
                    for ((label, value) in expandedDetails.entries) {
                        Row {
                            Text(
                                text = label,
                                fontSize = 16.sp
                            )

                            Text(
                                text = value.toString(),
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }

            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                var isMenuExpanded by remember {
                    mutableStateOf(false)
                }

                IconButton(
                    onClick = {
                        isMenuExpanded = !isMenuExpanded
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = stringResource(id = R.string.content_description_menu_button_icon)
                    )
                    DropdownMenu(
                        expanded = isMenuExpanded,
                        onDismissRequest = {
                            isMenuExpanded = !isMenuExpanded
                        }
                    ) {
                        dropDownMenuItems()
                    }
                }

                IconButton(
                    onClick = {
                        isCardExpanded = !isCardExpanded
                    }
                ) {
                    if (isCardExpanded) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = stringResource(id = R.string.content_description_collapse_button_icon)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = stringResource(id = R.string.content_description_expand_button_icon)
                        )
                    }
                }
            }
        }
    }
}