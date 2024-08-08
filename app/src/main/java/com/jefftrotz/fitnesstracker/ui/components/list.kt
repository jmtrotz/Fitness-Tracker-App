package com.jefftrotz.fitnesstracker.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jefftrotz.fitnesstracker.R

@Composable
fun <T> ItemList(list: List<T>, content: @Composable (T) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth().padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(items = list) { item ->
            content(item)
        }
    }
}

@Composable
fun ListItem(
    expandedDetails: List<String> = emptyList(),
    itemDetails: String,
    itemName: String,
    onClick: () -> Unit
) {
    var isCardExpanded by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Column(
                modifier = Modifier.padding(
                    start = 8.dp,
                    top = 8.dp,
                    bottom = 8.dp
                )) {
                Text(text = itemName, fontSize = 20.sp)
                Text(text = itemDetails, fontSize = 16.sp)

                if (isCardExpanded && expandedDetails.isNotEmpty()) {
                    for (string in expandedDetails) {
                        Text(
                            text = string,
                            fontSize = 16.sp
                        )
                    }
                }
            }

            if (expandedDetails.isNotEmpty()) {
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