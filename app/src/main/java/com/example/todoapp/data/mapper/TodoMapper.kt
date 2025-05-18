package com.example.todoapp.data.mapper

import com.example.todoapp.data.local.entities.TodoEntity
import com.example.todoapp.data.remote.dto.TodoDto
import com.example.todoapp.domain.model.Todo

// Entity (Room) → Domain
fun TodoEntity.toDomain(): Todo {
    return Todo(
        id = id,
        title = title,
        isCompleted = isCompleted
    )
}

// Domain → Entity (for saving custom todos if needed)
fun Todo.toEntity(): TodoEntity {
    return TodoEntity(
        id = id,
        title = title,
        isCompleted = isCompleted
    )
}

fun TodoDto.toEntity(): TodoEntity {
    return TodoEntity(
        id = this.id,
        title = this.title,
        isCompleted = this.completed
    )
}