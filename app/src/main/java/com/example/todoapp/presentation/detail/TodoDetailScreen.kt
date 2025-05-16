import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TodoDetailScreen(
    todoId: Int,
    repository: TodoRepository
) {
    val viewModel: TodoDetailViewModel = viewModel(
        factory = TodoDetailViewModelFactory(repository)
    )

    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(todoId) {
        viewModel.loadTodo(todoId)
    }

    when (val uiState = state) {
        is TodoDetailUIState.Loading -> CircularProgressIndicator()
        is TodoDetailUIState.Success -> {
            val todo = uiState.todo
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Title: ${todo.title}")
                Text("Completed: ${todo.completed}")
                Text("User ID: ${todo.userId}")
            }
        }
        is TodoDetailUIState.Error -> Text("Error: ${uiState.message}")
    }
}
