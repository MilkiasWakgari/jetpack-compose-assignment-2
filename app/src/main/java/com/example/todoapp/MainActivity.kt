package com.example.todoapp

import TodoAppTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.data.local.db.TodoDatabase
import com.example.todoapp.data.local.entities.TodoEntity
import com.example.todoapp.data.repository.TodoRepositoryImpl
import com.example.todoapp.domain.use_case.GetTodoByIdUseCase
import com.example.todoapp.domain.use_case.GetTodosUseCase
import com.example.todoapp.presentation.common.TodoListViewModelFactory
import com.example.todoapp.presentation.detail.TodoDetailViewModel
import com.example.todoapp.presentation.detail.TodoDetailViewModelFactory
import com.example.todoapp.presentation.list.TodoListViewModel
import com.example.todoapp.presentation.navigation.AppNavigation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = TodoDatabase.getDatabase(applicationContext)
        val dao = db.todoDao()
        val repository = TodoRepositoryImpl(dao)

        CoroutineScope(Dispatchers.IO).launch {
            val existing = dao.getTodos().firstOrNull()
            if (existing.isNullOrEmpty()) {
                dao.insertTodo(
                    TodoEntity(
                        id = 0, // Let Room auto-generate if using autoGenerate = true
                        title = "Welcome Task",
                        description = "This is your first todo!",
                        isCompleted = false
                    )
                )
            }
        }
        // 🆕 Create UseCases
        val getTodosUseCase = GetTodosUseCase(repository)
        val getTodoByIdUseCase = GetTodoByIdUseCase(repository)

        // ✅ Pass use cases into factories
        val todoListViewModelFactory = TodoListViewModelFactory(getTodosUseCase)
        val todoDetailViewModelFactory = TodoDetailViewModelFactory(getTodoByIdUseCase)

        val todoListViewModel = ViewModelProvider(this, todoListViewModelFactory)[TodoListViewModel::class.java]
        val todoDetailViewModel = ViewModelProvider(this, todoDetailViewModelFactory)[TodoDetailViewModel::class.java]

        setContent {
            TodoAppTheme {
                AppNavigation(
                    todoListViewModel = todoListViewModel,
                    todoDetailViewModel = todoDetailViewModel
                )
            }
        }
    }

}
