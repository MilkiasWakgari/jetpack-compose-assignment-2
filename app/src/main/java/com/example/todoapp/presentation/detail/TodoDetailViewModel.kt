package com.example.todoapp.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.domain.model.Todo
import com.example.todoapp.domain.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoDetailViewModel(
    private val repository: TodoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<TodoDetailUIState>(TodoDetailUIState.Loading)
    val uiState: StateFlow<TodoDetailUIState> = _uiState

    fun loadTodo(todoId: Int) {
        viewModelScope.launch {
            _uiState.value = TodoDetailUIState.Loading
            try {
                val todo = repository.getTodoById(todoId)
                if (todo != null) {
                    _uiState.value = TodoDetailUIState.Success(todo)
                } else {
                    _uiState.value = TodoDetailUIState.Error("Todo not found")
                }
            } catch (e: Exception) {
                _uiState.value = TodoDetailUIState.Error("Failed to load todo: ${e.localizedMessage}")
            }
        }
    }
}



