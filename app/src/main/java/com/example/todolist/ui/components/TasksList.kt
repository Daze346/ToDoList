package com.example.todolist.ui.components

import Task
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TasksList(
    modifier: Modifier,
    tasks: List<Task>,
    onTaskChecked: (Task) -> Unit,
    onDelete: (Task) -> Unit,
    returnTask: (Task) -> Unit
    ) {

    LazyColumn(
        modifier = modifier
    ){
        items(tasks) { task ->
            TaskItem(
                task = task,
                onCheckedChange = { onTaskChecked(task) },
                onDelete = { onDelete(task) },
                returnTask = returnTask
            )
        }
    }

}
