package com.example.notepad.ui.model.home

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notepad.data.todo
import com.example.notepad.ui.model.home.components.TodoItem


@Composable
fun HomeScreen(onNavigate: (todo?) -> Unit) {
    val viewModel = viewModel(Home_viewmodel::class.java)
    val state by viewModel.state.collectAsState()
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { onNavigate(null) }) {
            Icon(Icons.Default.Add, contentDescription = null)
        }
    },
      modifier =   Modifier.background(color = Color.Yellow)) {
        LazyColumn() {
            items(state.todolist) { todo ->
                TodoItem(
                    todo = todo,
                    onChecked = { viewModel.updatetodo(it, todo.id) },
                    onDelete = { viewModel.delete(it) },
                    onNavigation = { onNavigate(it) }
                )
            }
        }
    }
}