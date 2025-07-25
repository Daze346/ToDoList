package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope

import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment

import androidx.compose.foundation.background

import androidx.compose.material3.*

import androidx.compose.ui.graphics.toArgb
import com.example.todolist.ui.theme.*

import Task
import com.example.todolist.ui.components.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = LightBlack.toArgb()
        window.navigationBarColor = LightBlack.toArgb()
        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = false
            isAppearanceLightNavigationBars = false
        }


        val database = AppDatabase.getDatabase(this)
        val databaseDao = database.taskDao()


        setContent {
            val tasksState = remember { mutableStateOf(emptyList<Task>()) }

            var showCreateTaskDialog by remember { mutableStateOf(false) }


            LaunchedEffect(Unit) {
                withContext(Dispatchers.IO) {
                    val tasks = databaseDao.getAllTasks()
                    tasksState.value = tasks
                }
            }

            Scaffold { padding: PaddingValues ->
                Box(
                    modifier = Modifier
                        .padding(padding)
                        .background(LightBlack),
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Header()
                        TasksList(
                            modifier = Modifier.weight(1f),
                            tasks = tasksState.value,
                            onTaskChecked = {task ->
                                lifecycleScope.launch(Dispatchers.IO) {
                                    if (task.isCompleted){ databaseDao.setUncompleted(task.id) }
                                    else { databaseDao.setCompleted(task.id) }


                                    val updatedTasks = databaseDao.getAllTasks()
                                    withContext(Dispatchers.Main) {
                                        tasksState.value = updatedTasks
                                    }
                                }
                            },
                            onDelete = {task ->
                                lifecycleScope.launch(Dispatchers.IO) {
                                    databaseDao.deleteTask(taskId = task.id)

                                    val updatedTasks = databaseDao.getAllTasks()
                                    withContext(Dispatchers.Main) {
                                        tasksState.value = updatedTasks
                                    }
                                }
                            })

                        Footer(
                            onNewNoteClick = { showCreateTaskDialog = !showCreateTaskDialog }
                        )
                    }

                    if (showCreateTaskDialog) {
                        CreateTaskDialog(
                            modifier = Modifier.align(Alignment.Center),
                            onClose = { showCreateTaskDialog = false },
                            createNote = { noteName, noteDescription ->

                                lifecycleScope.launch(Dispatchers.IO) {
                                    val newTask = Task(
                                        title = noteName,
                                        description = noteDescription
                                    )
                                    databaseDao.addTask(newTask)

                                    val updatedTasks = databaseDao.getAllTasks()
                                    withContext(Dispatchers.Main) {
                                        tasksState.value = updatedTasks
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}