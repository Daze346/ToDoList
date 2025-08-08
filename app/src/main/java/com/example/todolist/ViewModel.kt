package com.example.todolist

import Task
import TaskDao
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewModel: ViewModel() {

    fun setCompletion(
        databaseDao: TaskDao,
        isCompleted: Boolean,
        taskId: Long,
        getUpdatedTasks: (List<Task>) -> Unit
    ){
        viewModelScope.launch {
            try{
                withContext(Dispatchers.IO) {
                    if (isCompleted) { databaseDao.setUncompleted(taskId) }
                    else { databaseDao.setCompleted(taskId) }

                    val updatedTasks = databaseDao.getAllTasks()
                    getUpdatedTasks(updatedTasks)
                }
            }
            catch (e: Exception){ Log.i("!!!", "${e.message}") }
        }
    }

    fun deleteTask(
        databaseDao: TaskDao,
        taskId: Long,
        getUpdatedTasks: (List<Task>) -> Unit
    ){
        viewModelScope.launch {
            try{
                withContext(Dispatchers.IO) {
                    databaseDao.deleteTask(taskId = taskId)

                    val updatedTasks = databaseDao.getAllTasks()
                    getUpdatedTasks(updatedTasks)
                }
            }
            catch (e: Exception){ Log.i("!!!", "${e.message}") }
        }
    }

    fun addTask(
        databaseDao: TaskDao,
        newTask: Task,
        getUpdatedTasks: (List<Task>) -> Unit
    ){
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    databaseDao.addTask(newTask)
                    val updatedTasks = databaseDao.getAllTasks()
                    getUpdatedTasks(updatedTasks)
                }
            } catch (e: Exception) {
                Log.i("!!!", "${e.message}")
            }
        }
    }

    fun renameTask(
        databaseDao: TaskDao,
        task: Task,
        newTaskTitle: String,
        newTaskDescription: String,
        getUpdatedTasks: (List<Task>) -> Unit
    ){
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    databaseDao.renameTask(
                        title = newTaskTitle,
                        description = newTaskDescription,
                        taskId = task.id
                    )

                    val updatedTasks = databaseDao.getAllTasks()
                    getUpdatedTasks(updatedTasks)
                }
            } catch (e: Exception) {
                Log.i("!!!", "${e.message}")
            }
        }
    }

}