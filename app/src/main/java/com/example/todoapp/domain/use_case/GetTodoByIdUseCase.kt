package com.example.todoapp.domain.use_case

import com.example.todoapp.domain.model.Todo
import com.example.todoapp.domain.repository.TodoRepository

class GetTodoByIdUseCase(private val repository: TodoRepository) {
    suspend operator fun invoke(id: Int): Todo? = repository.getTodoById(id)
}
