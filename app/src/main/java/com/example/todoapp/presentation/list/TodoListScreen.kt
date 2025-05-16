import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(
    navController: NavController,
    repository: TodoRepository
) {
    val viewModel: TodoListViewModel = viewModel(
        factory = TodoListViewModelFactory(repository)
    )

    val state by viewModel.uiState.collectAsState()

    Scaffold(topBar = { TopAppBar(title = { Text("Todo List") }) }) { padding ->
        Box(modifier = Modifier
            .padding(padding)
            .fillMaxSize()) {
            when (val uiState = state) {
                is TodoListUIState.Loading -> CircularProgressIndicator()
                is TodoListUIState.Success -> LazyColumn {
                    items(uiState.todos) { todo ->
                        TodoItemCard(todo = todo) {
                            navController.navigate("detail/${todo.id}")
                        }
                    }
                }
                is TodoListUIState.Error -> Column(modifier = Modifier.padding(16.dp)) {
                    Text(uiState.message, color = MaterialTheme.colorScheme.error)
                    Button(onClick = { viewModel.fetchTodos() }) {
                        Text("Retry")
                    }
                }
            }
        }
    }
}
