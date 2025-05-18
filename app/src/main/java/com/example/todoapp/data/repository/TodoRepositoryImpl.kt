package com.example.todoapp.data.repository

import com.example.todoapp.data.local.db.TodoDao
import com.example.todoapp.data.mapper.toDomain
import com.example.todoapp.data.mapper.toEntity
import com.example.todoapp.data.remote.api.TodoApiService
import com.example.todoapp.domain.model.Todo
import com.example.todoapp.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TodoRepositoryImpl(
    private val dao: TodoDao,
    private val api: TodoApiService
) : TodoRepository {

    override fun getTodos(): Flow<List<Todo>> = dao.getTodos().map { list ->
        list.map { it.toDomain() }
    }

    override suspend fun getTodoById(id: Int): Todo? {
        return dao.getTodoById(id)?.toDomain()
    }

    override suspend fun insertTodo(todo: Todo) {
        dao.insertTodo(todo.toEntity())
    }

    override suspend fun updateTodo(todo: Todo) {
        dao.updateTodo(todo.toEntity())
    }

    override suspend fun deleteTodo(todo: Todo) {
        dao.deleteTodo(todo.toEntity())
    }

    override suspend fun refreshTodos() {
        val todosFromApi = api.getTodos()
        val entities = todosFromApi.map { it.toEntity() }
        dao.insertTodos(entities)
    }
}
