package com.example.project6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.project6.ui.theme.Project6Theme
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Project6Theme {
                Box(modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = painterResource(id = R.drawable.download),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .alpha(0.8f),
                        contentScale = ContentScale.Crop
                    )

                    val navController = rememberNavController()
                    val tasks = remember { mutableStateListOf<String>() }

                    NavHost(
                        navController = navController,
                        startDestination = "task_list"
                    ) {
                        // Task List Screen
                        composable("task_list") {
                            TaskListScreen(
                                tasks = tasks,
                                onAddTaskClick = { navController.navigate("create_task") },
                                // NEW: Navigate to detail screen when a task is clicked
                                onTaskClick = { task ->
                                    val encodedTask = URLEncoder.encode(task, StandardCharsets.UTF_8.toString())
                                    navController.navigate("task_detail/$encodedTask")
                                }
                            )
                        }

                        // Create Task Screen
                        composable("create_task") {
                            CreateTaskScreen(
                                onSaveTask = { newTask ->
                                    tasks.add(newTask)
                                    navController.popBackStack()
                                },
                                onBackClick = { navController.popBackStack() }
                            )
                        }

                        // NEW: Task Detail Screen
                        composable(
                            route = "task_detail/{task}",
                            arguments = listOf(navArgument("task") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val taskArgument = backStackEntry.arguments?.getString("task") ?: ""
                            val decodedTask = URLDecoder.decode(taskArgument, StandardCharsets.UTF_8.toString())
                            TaskDetailScreen(
                                task = decodedTask,
                                onBackClick = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}
