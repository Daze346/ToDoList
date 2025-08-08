package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.todolist.ui.theme.*
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.ui.components.*
import com.example.todolist.ui.screens.MainScreen


class MainActivity : ComponentActivity() {

    lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        viewModel = ViewModelProvider(this)[ViewModel::class.java]

        val database = AppDatabase.getDatabase(this)

        setContent {

            SetSystemColor(
                systemBarsColor = LightBlack,
                statusBarColor = LightBlack,
                navigationBarColor = LightBlack,
                darkIcons = false
            )

            MainScreen(
                viewModel = viewModel,
                database = database
            )

        }
    }
}