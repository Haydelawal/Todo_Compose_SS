package com.example.todo_compose_ss.ui.screens.list

import ListContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.todo_compose_ss.R
import com.example.todo_compose_ss.ui.theme.fabBackgroundColor
import com.example.todo_compose_ss.ui.viewmodel.MySharedViewModel
import com.example.todo_compose_ss.utils.Action
import com.example.todo_compose_ss.utils.SearchAppBarState
import kotlinx.coroutines.launch

@Composable
fun ListScreen(
    action: Action,
    navigateToTaskScreen: (taskId: Int) -> Unit,
    mySharedViewModel: MySharedViewModel

) {

//    LaunchedEffect(key1 = true) {
//        mySharedViewModel.getAllTasks()
//        mySharedViewModel.readSortState()
//    }

    LaunchedEffect(key1 = action) {
        mySharedViewModel.handleDatabaseActions(action = action)
    }

//    val action by mySharedViewModel.action

    val allTasks by mySharedViewModel.allTasks.collectAsState()
    val searchedTasks by mySharedViewModel.searchedTasks.collectAsState()

    val sortState by mySharedViewModel.sortState.collectAsState()
    val lowPriorityTasks by mySharedViewModel.lowPriorityTasks.collectAsState()
    val highPriorityTasks by mySharedViewModel.highPriorityTasks.collectAsState()


    val searchAppBarState: SearchAppBarState = mySharedViewModel.searchAppBarState
    val searchTextState: String = mySharedViewModel.searchTextState

    val scaffoldState = rememberScaffoldState()

    DisplaySnackBar(
        scaffoldState = scaffoldState,
//        handleDatabaseActions = { mySharedViewModel.handleDatabaseAction(action = action) },
        onComplete = { mySharedViewModel.updateAction(newAction = it) },
        onUndoClicked = {
            mySharedViewModel.updateAction(newAction = it)
        },

        taskTitle = mySharedViewModel.title,
        action = action
    )

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            ListAppBar(
                mySharedViewModel = mySharedViewModel,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState
            )
        },
        content = {
            ListContent(
                allTasks = allTasks,
                navigateToTaskScreen = navigateToTaskScreen,

                lowPriorityTasks = lowPriorityTasks,
                highPriorityTasks = highPriorityTasks,
                sortState = sortState,
                onSwipeToDelete = { action, todoTask ->
//                    mySharedViewModel.action.value = action
                    mySharedViewModel.updateAction(newAction = action)
                    mySharedViewModel.updateTaskFields(selectedTask = todoTask)
                    scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                },

                searchAppBarState = searchAppBarState,
                searchedTasks = searchedTasks
            )
        },
        floatingActionButton = {
            ListFab(onFabClicked = navigateToTaskScreen)
        }
    )

}

@Composable
fun ListFab(
    onFabClicked: (taskId: Int) -> Unit

) {
    FloatingActionButton(
        onClick = {
            onFabClicked(-1)
        },
        backgroundColor = MaterialTheme.colors.fabBackgroundColor
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(id = R.string.fab_add_button),
            tint = Color.White
        )
    }
}


@Composable
private fun DisplaySnackBar(
    scaffoldState: ScaffoldState,
    onComplete: (Action) -> Unit,
    onUndoClicked: (Action) -> Unit,
    taskTitle: String,
    action: Action
) {

//    handleDatabaseActions()

    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = action) {
        if (action != Action.NO_ACTION) {
            scope.launch {
                val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                    message = setSnackBarMessage(action = action, taskTitle = taskTitle),
                    actionLabel = setActionLabel(action = action)
                )

                undoDeletedTask(
                    action = action,
                    snackBarResult = snackBarResult,
                    onUndoClicked = onUndoClicked
                )
            }

            onComplete(Action.NO_ACTION)
        }


    }

}

private fun setSnackBarMessage(
    action: Action,
    taskTitle: String
): String {

    return when (action) {
        Action.DELETE_ALL -> "All Tasks Removed"
        else -> "${action.name}: $taskTitle"
    }

}

private fun setActionLabel(action: Action): String {

    return if (action.name == "DELETE") {
        "UNDO"
    } else {
        "OKAY"
    }
}

private fun undoDeletedTask(
    action: Action,
    snackBarResult: SnackbarResult,
    onUndoClicked: (Action) -> Unit
) {

    if (snackBarResult == SnackbarResult.ActionPerformed
        && action == Action.DELETE
    ) {
        onUndoClicked(Action.UNDO)
    }
}