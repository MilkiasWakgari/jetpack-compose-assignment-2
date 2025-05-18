package com.example.todoapp.presentation.common

import RefreshTodosUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.domain.use_case.GetTodosUseCase
import com.example.todoapp.presentation.list.TodoListViewModel

class TodoListViewModelFactory(
    private val getTodosUseCase: GetTodosUseCase,
    private val refreshTodosUseCase: RefreshTodosUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TodoListViewModel(getTodosUseCase, refreshTodosUseCase) as T
    }
}

