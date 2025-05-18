package com.example.todoapp.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.domain.repository.TodoRepository
import com.example.todoapp.domain.use_case.GetTodosUseCase
import com.example.todoapp.presentation.list.TodoListViewModel

class TodoListViewModelFactory(
    private val repository: TodoRepository,
    private val getTodosUseCase: GetTodosUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TodoListViewModel(repository, getTodosUseCase) as T
    }
}
