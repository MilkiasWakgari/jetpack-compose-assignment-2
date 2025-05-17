package com.example.todoapp.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.domain.model.Todo
import com.example.todoapp.domain.use_case.GetTodoByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoDetailViewModel(
    private val getTodoByIdUseCase: GetTodoByIdUseCase
) : ViewModel() {

    private val _todoState = MutableStateFlow<Todo?>(null)
    val todoState: StateFlow<Todo?> = _todoState

    fun loadTodoById(id: Int) {
        viewModelScope.launch {
            val todo = getTodoByIdUseCase(id)
            _todoState.value = todo
        }
    }
}
