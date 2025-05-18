package com.example.todoapp

import RefreshTodosUseCase
import TodoAppTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.data.local.db.TodoDatabase
import com.example.todoapp.data.local.entities.TodoEntity
import com.example.todoapp.data.remote.api.RetrofitInstance
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

        // 1. Setup local database
        val db = TodoDatabase.getDatabase(applicationContext)
        val dao = db.todoDao()

        // 2. Setup API client
        val api = RetrofitInstance.api

        // 3. Create repository
        val repository = TodoRepositoryImpl(dao, api)

        // 4. Insert a default todo if empty
//        CoroutineScope(Dispatchers.IO).launch {
//            val existing = dao.getTodos().firstOrNull()
//            if (existing.isNullOrEmpty()) {
//                dao.insertTodo(
//                    TodoEntity(
//                        id = 0,
//                        title = "Welcome Task",
//                        isCompleted = false
//                    )
//                )
//            }
//        }

        // 5. Use Cases
        val getTodosUseCase = GetTodosUseCase(repository)
        val refreshTodosUseCase = RefreshTodosUseCase(repository)
        val getTodoByIdUseCase = GetTodoByIdUseCase(repository)

        // 6. ViewModel Factories
        val todoListViewModelFactory = TodoListViewModelFactory(repository, getTodosUseCase)

        val todoDetailViewModelFactory = TodoDetailViewModelFactory(getTodoByIdUseCase)

        // 7. ViewModels
        val todoListViewModel = ViewModelProvider(this, todoListViewModelFactory)[TodoListViewModel::class.java]
        val todoDetailViewModel = ViewModelProvider(this, todoDetailViewModelFactory)[TodoDetailViewModel::class.java]

        // 8. Compose UI
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
