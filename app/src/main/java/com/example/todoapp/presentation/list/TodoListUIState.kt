import com.example.todoapp.domain.model.Todo

sealed class TodoListUIState {
    object Loading : TodoListUIState()
    data class Success(val todos: List<Todo>) : TodoListUIState()
    data class Error(val message: String) : TodoListUIState()
}
