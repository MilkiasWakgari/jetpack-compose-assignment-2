package com.example.todoapp.presentation.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.todoapp.presentation.common.TodoListUIState
import com.example.todoapp.presentation.components.TodoItemCard
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun TodoListScreen(
    navController: NavController,
    viewModel: TodoListViewModel
) {
    val state by viewModel.uiState.collectAsState()

    when (state) {
        is TodoListUIState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is TodoListUIState.Success -> {
            val todos = (state as TodoListUIState.Success).todos
            if (todos.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "No to-dos found.")
                }
            } else {
                LazyColumn {
                    items(todos) { todo ->
                        TodoItemCard(
                            todo = todo,
                            onItemClick = { navController.navigate("detail/${todo.id}") },
                            modifier = Modifier
                        )
                    }
                }
            }
        }
        is TodoListUIState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = (state as TodoListUIState.Error).message)
            }
        }
    }
}
