package com.example.project6

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color // 1. IMPORT THIS
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTaskScreen(
    onSaveTask: (String) -> Unit,
    onBackClick: () -> Unit
) {
    var taskText by remember { mutableStateOf("") }

    // 2. THIS IS THE FIX: Make the main layout transparent
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent) // Make background see-through
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Add a New Task", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = taskText,
            onValueChange = { taskText = it },
            label = { Text("Enter task description") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Button(onClick = onBackClick) {
                Text("Back")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    if (taskText.isNotBlank()) {
                        onSaveTask(taskText)
                    }
                },
                enabled = taskText.isNotBlank()
            ) {
                Text("Save Task")
            }
        }
    }
}
