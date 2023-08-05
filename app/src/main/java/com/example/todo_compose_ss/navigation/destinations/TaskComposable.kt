package com.example.todo_compose_ss.navigation.destinations

import android.annotation.SuppressLint
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.example.todo_compose_ss.ui.screens.task.TaskScreen
import com.example.todo_compose_ss.ui.viewmodel.MySharedViewModel
import com.example.todo_compose_ss.utils.Action
import com.example.todo_compose_ss.utils.Constants
import com.example.todo_compose_ss.utils.Constants.TASK_ARGUMENT_KEY
import com.example.todo_compose_ss.utils.Constants.TASK_SCREEN

@SuppressLint("SuspiciousIndentation")
fun NavGraphBuilder.taskComposable(
    mySharedViewModel: MySharedViewModel,
    navigateToListScreen: (Action) -> Unit
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) { navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)

        LaunchedEffect(key1 = taskId){
            mySharedViewModel.getSelectedTask(taskId = taskId)
        }

        // COMMIT ME
        // COMMIT ME
        // COMMIT ME
        // COMMIT ME
        // COMMIT ME
        // COMMIT ME
        // COMMIT ME
        // COMMIT ME
        // COMMIT ME

        val selectedTask by mySharedViewModel.selectedTask.collectAsState()

        LaunchedEffect(key1 = selectedTask) {
            // remember to add this to check and undo deleted task
            if (selectedTask != null || taskId == -1)
            mySharedViewModel.updateTaskFields(selectedTask = selectedTask)
        }

        TaskScreen(
            selectedTask = selectedTask,
            mySharedViewModel = mySharedViewModel,
            navigateToListScreen = navigateToListScreen
        )
    }
}