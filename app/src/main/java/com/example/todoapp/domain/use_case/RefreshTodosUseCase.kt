import com.example.todoapp.domain.repository.TodoRepository

// domain/use_case/RefreshTodosUseCase.kt
class RefreshTodosUseCase(
    private val repository: TodoRepository
) {
    suspend operator fun invoke() {
        repository.refreshTodos()
    }
}
