// TodoDetailUIState.kt
sealed class TodoDetailUIState {
    object Loading : TodoDetailUIState()
    data class Success(val todo: Todo) : TodoDetailUIState()
    data class Error(val message: String) : TodoDetailUIState()
}
