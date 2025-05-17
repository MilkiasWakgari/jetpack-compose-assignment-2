package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.example.todoapp.data.local.db.TodoDatabase
import com.example.todoapp.data.repository.TodoRepositoryImpl
import com.example.todoapp.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Create Room database instance
        val database = TodoDatabase.getDatabase(applicationContext)
        val todoDao = database.todoDao()
        
        // Create repository with only local storage
        val repository = TodoRepositoryImpl(todoDao)

        setContent {
            MaterialTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    AppNavigation(repository = repository)
                }
            }
        }
    }
}
