package com.example.notepad.ui.model.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.notepad.data.todo

@Composable
fun TodoItem(
    todo: todo,
    onChecked: (Boolean) -> Unit,
    onDelete: (todo) -> Unit,
    onNavigation: (todo) -> Unit
) {

    Card(
        backgroundColor = MaterialTheme.colors.primarySurface,
        modifier = Modifier.fillMaxWidth()
            .padding(16.dp)
            .background(color = Color.Yellow)
            .clip(RoundedCornerShape(6.dp))
            .clickable { onNavigation(todo) },


    ) {
        Spacer(modifier = Modifier.size(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(checked = todo.isDone,
                onCheckedChange = {
                    onChecked(it)
                })
            Spacer(modifier = Modifier.size(16.dp))
            Column(modifier = Modifier
                .padding(10.dp)
                .weight(1f)

               ) {
                Text(todo.todo, style = MaterialTheme.typography.subtitle2)
                Spacer(modifier = Modifier.size(16.dp))
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(todo.time, style = MaterialTheme.typography.body2)
                }

            }
            Spacer(modifier = Modifier.size(16.dp))
            IconButton(onClick = { onDelete(todo) }) {
                Icon(imageVector = Icons.Default.Clear, contentDescription = null)

            }

        }

    }
}
