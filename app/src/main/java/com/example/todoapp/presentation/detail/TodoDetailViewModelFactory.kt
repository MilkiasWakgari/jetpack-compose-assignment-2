package com.example.todoapp.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.domain.use_case.GetTodoByIdUseCase

class TodoDetailViewModelFactory(
    private val getTodoByIdUseCase: GetTodoByIdUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TodoDetailViewModel(getTodoByIdUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
