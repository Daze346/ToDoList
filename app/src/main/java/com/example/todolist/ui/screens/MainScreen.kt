package com.example.todolist.ui.screens

import AppDatabase
import Task
import TaskDao
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.todolist.ViewModel
import com.example.todolist.ui.components.CreateTaskDialog
import com.example.todolist.ui.components.Footer
import com.example.todolist.ui.components.Header
import com.example.todolist.ui.components.RenameTaskDialog
import com.example.todolist.ui.components.TasksList
import com.example.todolist.ui.theme.LightBlack
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun MainScreen(
    viewModel: ViewModel,
    database: AppDatabase
){
    val databaseDao: TaskDao = database.taskDao()
    val tasksState = remember { mutableStateOf(emptyList<Task>()) }

    var showCreateTaskDialog by remember { mutableStateOf(false) }
    var showRenameTaskDialog by remember { mutableStateOf(false) }
    var currentTaskForRename: Task? = null

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
                        viewModel.setCompletion(
                            databaseDao = databaseDao,
                            isCompleted = task.isCompleted,
                            taskId = task.id,
                            getUpdatedTasks = { updatedListOfTask -> tasksState.value = updatedListOfTask }
                        )
                    },
                    onDelete = {task ->
                        viewModel.deleteTask(
                            databaseDao = databaseDao,
                            taskId = task.id,
                            getUpdatedTasks = { updatedListOfTask -> tasksState.value = updatedListOfTask }
                        )
                    },
                    returnTask = { task ->

                        showRenameTaskDialog = true
                        currentTaskForRename = task


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
                        val newTask = Task(
                            title = noteName,
                            description = noteDescription
                        )

                        viewModel.addTask(
                            databaseDao = databaseDao,
                            newTask = newTask,
                            getUpdatedTasks = { updatedListOfTask -> tasksState.value = updatedListOfTask }
                        )

                    }
                )
            }

            if (showRenameTaskDialog && currentTaskForRename != null){
                RenameTaskDialog(
                    modifier = Modifier.align(Alignment.Center),
                    onClose = { showRenameTaskDialog = false },
                    task = currentTaskForRename,
                    renameNote = { newTaskTitle, newTaskDescription, task ->
                        viewModel.renameTask(
                            databaseDao = databaseDao,
                            task = task,
                            newTaskTitle = newTaskTitle,
                            newTaskDescription = newTaskDescription,
                            getUpdatedTasks = { updatedListOfTask -> tasksState.value = updatedListOfTask }
                        )
                    }
                )
            }






        }
    }
}