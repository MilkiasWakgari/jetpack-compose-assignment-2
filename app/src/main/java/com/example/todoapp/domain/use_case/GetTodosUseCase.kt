package com.example.todoapp.domain.use_case

import com.example.todoapp.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import com.example.todoapp.domain.model.Todo

class GetTodosUseCase(private val repository: TodoRepository) {
    operator fun invoke(): Flow<List<Todo>> = repository.getTodos()
}
