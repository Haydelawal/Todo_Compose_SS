package com.example.todo_compose_ss.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.todo_compose_ss.navigation.destinations.listComposable
import com.example.todo_compose_ss.navigation.destinations.taskComposable
import com.example.todo_compose_ss.ui.viewmodel.MySharedViewModel
import com.example.todo_compose_ss.utils.Constants.LIST_SCREEN

@Composable
fun SetUpNavigation(
    navController: NavHostController,
    mySharedViewModel: MySharedViewModel
) {

    val screen = remember(navController) {
        Screens(navController = navController)
    }

    NavHost(navController = navController, startDestination = LIST_SCREEN) {
        listComposable(
            navigateToTaskScreen = screen.task,
            mySharedViewModel= mySharedViewModel
        )

        taskComposable(
            navigateToListScreen = screen.list,
            mySharedViewModel = mySharedViewModel
        )
    }

}