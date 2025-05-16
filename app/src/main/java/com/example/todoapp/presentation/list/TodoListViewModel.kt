import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoListViewModel(
    private val repository: TodoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<TodoListUIState>(TodoListUIState.Loading)
    val uiState: StateFlow<TodoListUIState> = _uiState

    init {
        fetchTodos()
    }

    fun fetchTodos() {
        viewModelScope.launch {
            _uiState.value = TodoListUIState.Loading
            try {
                val todos = repository.getTodos()
                _uiState.value = TodoListUIState.Success(todos)
            } catch (e: Exception) {
                _uiState.value = TodoListUIState.Error("Failed to fetch todos: ${e.localizedMessage}")
            }
        }
    }
}
