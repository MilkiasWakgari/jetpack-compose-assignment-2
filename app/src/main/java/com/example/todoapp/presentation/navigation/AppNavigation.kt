package com.example.todoapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.presentation.detail.TodoDetailScreen
import com.example.todoapp.presentation.detail.TodoDetailViewModel
import com.example.todoapp.presentation.list.TodoListScreen
import com.example.todoapp.presentation.list.TodoListViewModel

@Composable
fun AppNavigation(
    todoListViewModel: TodoListViewModel,
    todoDetailViewModel: TodoDetailViewModel
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            // ✅ Use passed ViewModel directly
            TodoListScreen(navController = navController, viewModel = todoListViewModel)
        }

        composable("detail/{todoId}") { backStackEntry ->
            val todoId = backStackEntry.arguments?.getString("todoId")?.toIntOrNull() ?: return@composable
            // ✅ Use passed ViewModel directly
            todoDetailViewModel.loadTodoById(todoId)
            TodoDetailScreen(todoId = todoId, viewModel = todoDetailViewModel)
        }
    }
}

