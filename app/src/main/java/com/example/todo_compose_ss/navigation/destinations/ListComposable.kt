package com.example.todo_compose_ss.navigation.destinations

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
//import androidx.navigation.compose.navArgument
import androidx.navigation.navArgument
import com.example.todo_compose_ss.ui.screens.list.ListScreen
import com.example.todo_compose_ss.ui.viewmodel.MySharedViewModel
import com.example.todo_compose_ss.utils.Action
import com.example.todo_compose_ss.utils.Constants.LIST_ARGUMENT_KEY
import com.example.todo_compose_ss.utils.Constants.LIST_SCREEN
import com.example.todo_compose_ss.utils.toAction

fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    mySharedViewModel: MySharedViewModel
){
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY){
            type = NavType.StringType
        })
    ){ navBackStackEntry ->

        val action = navBackStackEntry.arguments?.getString(LIST_ARGUMENT_KEY).toAction()

        var myAction by rememberSaveable {
            mutableStateOf(Action.NO_ACTION)
        }

        LaunchedEffect(key1 = myAction ){

            if (action != myAction) {
                myAction = action
                mySharedViewModel.updateAction(newAction = action)
            }

        }

        val databaseAction = mySharedViewModel.action

//        Log.d("ListComposable", action.name)

        ListScreen(
            action= databaseAction,navigateToTaskScreen = navigateToTaskScreen, mySharedViewModel= mySharedViewModel)
    }
}