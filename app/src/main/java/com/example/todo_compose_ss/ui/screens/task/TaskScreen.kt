package com.example.todo_compose_ss.ui.screens.task

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.todo_compose_ss.data.models.Priority
import com.example.todo_compose_ss.data.models.TodoTask
import com.example.todo_compose_ss.ui.viewmodel.MySharedViewModel
import com.example.todo_compose_ss.utils.Action

@Composable
fun TaskScreen(
    selectedTask: TodoTask?,
    mySharedViewModel: MySharedViewModel,
    navigateToListScreen: (Action) -> Unit
) {
    val title: String by mySharedViewModel.title
    val description: String by mySharedViewModel.description
    val priority: Priority by mySharedViewModel.priority

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TaskAppBar(
                selectedTask = selectedTask,
                navigateToListScreen = { action ->
                    if (action == Action.NO_ACTION) {
                        navigateToListScreen(action)
                    } else {
                        if (mySharedViewModel.validateFields()) {
                            navigateToListScreen(action)
                        } else {
                            displayAlertDialog(context = context)
                        }
                    }
                }
            )
        },
        content = {
            TaskContent(
                title = title,
                onTitleChange = {
                    // if the text is longer than 20 it won't update
                    mySharedViewModel.updateTitle(it)
                },
                description = description,
                onDescriptionChange = {
                    mySharedViewModel.description.value = it
                },
                priority = priority,
                onPrioritySelected = {
                    mySharedViewModel.priority.value = it
                }
            )
        }
    )
}

fun displayToast(context: Context) {

    Toast.makeText(context, "Input Field Can Not Be Empty", Toast.LENGTH_SHORT).show()
}

fun displayAlertDialog(context: Context){
    val builder = AlertDialog.Builder(context)
    builder.setPositiveButton("OKAY") { _, _ ->

    }
    builder.setCancelable(false)
    builder.setMessage("Input Field Can Not Be Empty")

    builder.create().show()
}

