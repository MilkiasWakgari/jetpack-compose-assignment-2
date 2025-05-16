class GetTodoByIdUseCase(private val repository: TodoRepository) {
    suspend operator fun invoke(id: Int) = repository.getTodoById(id)
}
