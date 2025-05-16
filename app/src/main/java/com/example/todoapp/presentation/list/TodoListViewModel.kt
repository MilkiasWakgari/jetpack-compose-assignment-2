package com.example.todoapp.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.domain.model.Todo
import com.example.todoapp.domain.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class TodoListState(
    val todos: List<Todo> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class TodoListViewModel(
    private val repository: TodoRepository
) : ViewModel() {
    private val _state = MutableStateFlow(TodoListState())
    val state: StateFlow<TodoListState> = _state.asStateFlow()

    init {
        fetchTodos()
    }

    fun fetchTodos() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                repository.getTodos().collect { todos ->
                    _state.value = _state.value.copy(
                        todos = todos,
                        isLoading = false,
                        error = null
                    )
                }
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message ?: "An error occurred"
                )
            }
        }
    }
}
