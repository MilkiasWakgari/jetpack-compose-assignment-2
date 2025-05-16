package com.example.todoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.domain.repository.TodoRepository
import com.example.todoapp.presentation.detail.TodoDetailScreen
import com.example.todoapp.presentation.detail.TodoDetailViewModel
import com.example.todoapp.presentation.detail.TodoDetailViewModelFactory
import com.example.todoapp.presentation.list.TodoListScreen
import com.example.todoapp.presentation.list.TodoListViewModel
import com.example.todoapp.presentation.list.TodoListViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AppNavigation(repository: TodoRepository) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            val listViewModel: TodoListViewModel = viewModel(
                factory = TodoListViewModelFactory(repository)
            )
            TodoListScreen(navController = navController, viewModel = listViewModel)
        }

        composable("detail/{todoId}") { backStackEntry ->
            val todoId = backStackEntry.arguments?.getString("todoId")?.toIntOrNull() ?: return@composable
            val detailViewModel: TodoDetailViewModel = viewModel(
                factory = TodoDetailViewModelFactory(repository)
            )
            TodoDetailScreen(todoId = todoId, viewModel = detailViewModel)
        }
    }
}
