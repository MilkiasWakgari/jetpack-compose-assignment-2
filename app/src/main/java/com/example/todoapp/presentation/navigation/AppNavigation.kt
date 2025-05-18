package com.example.todoapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.presentation.add.AddTodoScreen
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
            TodoListScreen(
                navController = navController,
                viewModel = todoListViewModel
            )
        }

        composable("detail/{todoId}") { backStackEntry ->
            val todoId = backStackEntry.arguments?.getString("todoId")?.toIntOrNull() ?: return@composable
            TodoDetailScreen(
                todoId = todoId,
                viewModel = todoDetailViewModel,
                navController = navController // Pass this!
            )
        }

    }
}


