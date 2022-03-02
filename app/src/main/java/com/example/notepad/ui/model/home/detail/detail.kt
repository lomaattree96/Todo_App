package com.example.notepad.ui.model.home.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notepad.data.todo

@Composable
fun DetailScreen(
    selectId: Long,
    onNavigate: () -> Unit
) {
    val viewModel = viewModel(
        Detail_viewmodel::class.java,
        factory = DetailViewModelFactory(selectId)
    )
    val state by viewModel.state.collectAsState()
    DetailScreenComponent(
        todoText = state.todo,
        onTodoTextChange = { viewModel.onTextChange(it) },
        todoTime = state.time,
        onTodoTimeChange = { viewModel.onTimeChange(it) },
        onSaveTodo = { viewModel.insert(it) },
        selectId = state.selectId,
        onNavigate = { onNavigate() }
    )

}

@Composable
fun DetailScreenComponent(
    todoText: String,
    onTodoTextChange: (String) -> Unit,
    todoTime: String,
    onTodoTimeChange: (String) -> Unit,
    onNavigate: () -> Unit,
    onSaveTodo: (todo) -> Unit,
    selectId: Long,
) {
    val isEdittodo = selectId == -1L
    Column(
        modifier = Modifier.fillMaxWidth()
            .fillMaxSize()
            .background(color = MaterialTheme.colors.secondaryVariant),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(16.dp))
        OutlinedTextField(
            value = todoText,
            onValueChange = { onTodoTextChange(it) },
            label = { Text(text = "Enter the todo text") }
        )

        Spacer(modifier = Modifier.size(16.dp))
        OutlinedTextField(
            value = todoTime,
            onValueChange = { onTodoTimeChange(it) },
            label = { Text(text = "Enter the  time") }
        )
        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = {
            val Todo = if (isEdittodo) todo(todoText, todoTime)
            else todo(todoText, todoTime, id = selectId)
            onSaveTodo(Todo)
            onNavigate()
        },
        modifier = Modifier
            .background(color = Color.DarkGray))
        {
            val text = if (isEdittodo) "save todo"
            else "update todo"
            Text(text = text)
        }

    }


}