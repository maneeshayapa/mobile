package com.example.project6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.project6.ui.theme.Project6Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Project6Theme {
                val navController = rememberNavController()
                val tasks = remember { mutableStateListOf<String>() } // Shared state for tasks ✅

                NavHost(
                    navController = navController,
                    startDestination = "task_list"
                ) {
                    // 🏠 Task List Screen
                    composable("task_list") {
                        TaskListScreen(
                            tasks = tasks,
                            onAddTaskClick = { navController.navigate("create_task") }
                        )
                    }

                    // ➕ Create Task Screen
                    composable("create_task") {
                        CreateTaskScreen(
                            onSaveTask = { newTask ->
                                tasks.add(newTask)
                                navController.popBackStack() // Go back automatically
                            },
                            onBackClick = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}