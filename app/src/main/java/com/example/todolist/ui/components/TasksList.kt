package com.example.todolist.ui.components

import Task
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable

@Composable
fun TasksList(tasks: List<Task>, onTaskChecked: (Task) -> Unit, onDelete: (Task) -> Unit){

    LazyColumn {
        items(tasks) { task ->
            TaskItem(
                task = task,
                onCheckedChange = { onTaskChecked(task) },
                onDelete = { onDelete(task) }
            )
        }
    }
}
