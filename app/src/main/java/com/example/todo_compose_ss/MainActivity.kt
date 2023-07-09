package com.example.todo_compose_ss

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels

import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.todo_compose_ss.navigation.SetUpNavigation
import com.example.todo_compose_ss.ui.theme.Todo_Compose_SSTheme
import com.example.todo_compose_ss.ui.viewmodel.MySharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    
    private lateinit var navController: NavHostController
    private val mySharedViewModel: MySharedViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Todo_Compose_SSTheme {
                navController = rememberNavController()
                SetUpNavigation(navController = navController,
                mySharedViewModel = mySharedViewModel)
            }
        }
    }
}

