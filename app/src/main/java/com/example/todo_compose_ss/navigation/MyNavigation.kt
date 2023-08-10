package com.example.todo_compose_ss.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.todo_compose_ss.navigation.destinations.listComposable
import com.example.todo_compose_ss.navigation.destinations.splashComposable
import com.example.todo_compose_ss.navigation.destinations.taskComposable
import com.example.todo_compose_ss.ui.viewmodel.MySharedViewModel
import com.example.todo_compose_ss.utils.Constants.LIST_SCREEN
import com.example.todo_compose_ss.utils.Constants.SPLASH_SCREEN
import com.google.accompanist.navigation.animation.AnimatedNavHost

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SetUpNavigation(
    navController: NavHostController,
    mySharedViewModel: MySharedViewModel
) {

    val screen = remember(navController) {
        Screens(navController = navController)
    }

    AnimatedNavHost(navController = navController, startDestination = SPLASH_SCREEN) {

        splashComposable (navigateToListScreen = screen.splash)

        listComposable(
            navigateToTaskScreen = screen.list,
            mySharedViewModel= mySharedViewModel
        )

        taskComposable(
            navigateToListScreen = screen.task,
            mySharedViewModel = mySharedViewModel
        )
    }

}