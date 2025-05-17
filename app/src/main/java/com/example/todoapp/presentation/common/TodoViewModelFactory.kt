package com.example.todoapp.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.domain.use_case.GetTodosUseCase
import com.example.todoapp.presentation.list.TodoListViewModel

class TodoListViewModelFactory(
    private val getTodosUseCase: GetTodosUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TodoListViewModel(getTodosUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
