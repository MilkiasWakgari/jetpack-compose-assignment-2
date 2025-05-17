package com.example.todoapp.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.domain.use_case.GetTodosUseCase
import com.example.todoapp.presentation.common.TodoListUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoListViewModel(
    private val getTodosUseCase: GetTodosUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<TodoListUIState>(TodoListUIState.Loading)
    val uiState: StateFlow<TodoListUIState> = _uiState

    init {
        getTodoList()
    }

    private fun getTodoList() {
        viewModelScope.launch {
            getTodosUseCase().collect { todos ->
                _uiState.value = TodoListUIState.Success(todos)
            }
        }
    }
}

