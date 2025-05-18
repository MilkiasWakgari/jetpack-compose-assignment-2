package com.example.todoapp.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.domain.model.Todo
import com.example.todoapp.domain.repository.TodoRepository
import com.example.todoapp.domain.use_case.GetTodosUseCase
import com.example.todoapp.presentation.common.TodoListUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoListViewModel(
    private val repository: TodoRepository,
    private val getTodosUseCase: GetTodosUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<TodoListUIState>(TodoListUIState.Loading)
    val uiState: StateFlow<TodoListUIState> = _uiState

    init {
        getTodoList()
    }

    private fun getTodoList() {
        viewModelScope.launch {
            try {
                getTodosUseCase().collect { todos ->
                    // Sort descending by ID (or creation time if available)
                    val sorted = todos.sortedByDescending { it.id }
                    _uiState.value = TodoListUIState.Success(sorted)
                }
            } catch (e: Exception) {
                _uiState.value = TodoListUIState.Error("Failed to load todos.")
            }
        }
    }


    fun addTodo(title: String) {
        viewModelScope.launch {
            val newTodo = Todo(id = 0, title = title, isCompleted = false)
            repository.insertTodo(newTodo)
            getTodoList()
        }
    }
}
