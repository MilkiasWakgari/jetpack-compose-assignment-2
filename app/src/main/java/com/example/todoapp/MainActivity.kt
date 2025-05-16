import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todoapp.data.repository.TodoRepositoryImpl
import com.example.todoapp.data.local.db.TodoDatabase
import com.example.todoapp.data.local.db.TodoDao
import com.example.todoapp.domain.repository.TodoRepository

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Create Room database instance
        val database = TodoDatabase.getDatabase(applicationContext)
        val todoDao = database.todoDao()
        
        // Create repository with only local storage
        val repository = TodoRepositoryImpl(todoDao)
        
        setContent {
            MyAppTheme {
                AppNavigation(repository = repository)
            }
        }
    }
}

@Composable
fun AppNavigation(repository: TodoRepository) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            TodoListScreen(navController = navController, repository = repository)
        }
        composable(
            route = "detail/{todoId}",
            arguments = listOf(navArgument("todoId") { type = NavType.IntType })
        ) { backStackEntry ->
            val todoId = backStackEntry.arguments?.getInt("todoId") ?: return@composable
            TodoDetailScreen(todoId = todoId, repository = repository)
        }
    }
}
