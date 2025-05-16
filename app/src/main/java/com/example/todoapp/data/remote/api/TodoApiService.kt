package com.example.todoapp.data.remote.api

import com.example.todoapp.data.remote.dto.TodoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface TodoApiService {
    @GET("/todos")
    suspend fun getTodos(): List<TodoDto>

    @GET("/todos/{id}")
    suspend fun getTodoById(@Path("id") id: Int): TodoDto
}
