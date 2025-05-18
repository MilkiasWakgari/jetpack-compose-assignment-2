package com.example.todoapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todoapp.domain.model.Todo

@Entity(tableName = "todos")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val isCompleted: Boolean
)
