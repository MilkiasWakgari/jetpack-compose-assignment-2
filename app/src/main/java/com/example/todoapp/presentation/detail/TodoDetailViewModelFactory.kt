package com.example.todoapp.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.domain.repository.TodoRepository

class TodoDetailViewModelFactory(
    private val repository: TodoRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TodoDetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
