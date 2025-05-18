package com.example.todoapp.presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TodoDetailScreen(
    viewModel: TodoDetailViewModel,
    todoId: Int
) {
    val todo by viewModel.todoState.collectAsState()

    LaunchedEffect(todoId) {
        viewModel.loadTodoById(todoId)
    }

    todo?.let {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Title: ${it.title}", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = if (it.isCompleted) "Completed" else "Pending")
        }
    } ?: run {
        CircularProgressIndicator()
    }
}
