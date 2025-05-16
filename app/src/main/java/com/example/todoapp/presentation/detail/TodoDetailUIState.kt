// TodoDetailUIState.kt
package com.example.todoapp.presentation.detail

import com.example.todoapp.domain.model.Todo

sealed class TodoDetailUIState {
    object Loading : TodoDetailUIState()
    data class Success(val todo: Todo) : TodoDetailUIState()
    data class Error(val message: String) : TodoDetailUIState()
}
