import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.todoapp.data.local.db.TodoDatabase
import com.example.todoapp.data.repository.TodoRepositoryImpl

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
