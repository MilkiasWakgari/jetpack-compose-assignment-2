import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoDetailViewModel(
    private val repository: TodoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<TodoDetailUIState>(TodoDetailUIState.Loading)
    val uiState: StateFlow<TodoDetailUIState> = _uiState

    fun loadTodo(todoId: Int) {
        viewModelScope.launch {
            _uiState.value = TodoDetailUIState.Loading
            try {
                val todo = repository.getTodoById(todoId)
                _uiState.value = TodoDetailUIState.Success(todo)
            } catch (e: Exception) {
                _uiState.value = TodoDetailUIState.Error("Failed to load todo: ${e.localizedMessage}")
            }
        }
    }
}



