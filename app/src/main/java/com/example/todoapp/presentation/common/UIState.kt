package com.example.todoapp.presentation.common

import com.example.todoapp.domain.model.Todo

// UI state for the Todo List Screen
sealed class TodoListUIState {
    object Loading : TodoListUIState()
    data class Success(val todos: List<Todo>) : TodoListUIState()
    data class Error(val message: String) : TodoListUIState()
}

// UI state for the Todo Detail Screen
sealed class TodoDetailUIState {
    object Loading : TodoDetailUIState()
    data class Success(val todo: Todo) : TodoDetailUIState()
    data class Error(val message: String) : TodoDetailUIState()
}
