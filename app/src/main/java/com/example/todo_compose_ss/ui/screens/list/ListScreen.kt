package com.example.todo_compose_ss.ui.screens.list

import ListContent
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.todo_compose_ss.R
import com.example.todo_compose_ss.ui.theme.fabBackgroundColor
import com.example.todo_compose_ss.ui.viewmodel.MySharedViewModel
import com.example.todo_compose_ss.utils.SearchAppBarState

@Composable
fun ListScreen(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    mySharedViewModel: MySharedViewModel

) {

    LaunchedEffect(key1 = true){
        mySharedViewModel.getAllTasks()
    }

    val allTasks by mySharedViewModel.allTasks.collectAsState()

    val searchAppBarState: SearchAppBarState by mySharedViewModel.searchAppBarState
    val searchTextState: String by mySharedViewModel.searchTextState

    Scaffold(
        topBar = {
            ListAppBar(
                mySharedViewModel = mySharedViewModel,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState
            )
        },
        content = {
            ListContent(tasks = allTasks, navigateToTaskScreen = navigateToTaskScreen)
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


//@Composable
//@Preview
//private fun ListScreenPreview() {
//
//    ListScreen(navigateToTaskScreen = {})
//
//}