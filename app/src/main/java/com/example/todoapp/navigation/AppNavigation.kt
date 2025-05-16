import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AppNavigation(repository: TodoRepository) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "list") {
        composable("list") {
            val listViewModel: TodoListViewModel = viewModel(
                factory = TodoListViewModelFactory(repository)
            )
            TodoListScreen(navController, listViewModel)
        }

        composable("detail/{todoId}") { backStackEntry ->
            val todoId = backStackEntry.arguments?.getString("todoId")?.toIntOrNull() ?: return@composable

            val detailViewModel: TodoDetailViewModel = viewModel(
                factory = TodoDetailViewModelFactory(repository)
            )
            TodoDetailScreen(todoId, detailViewModel)
        }
    }
}
