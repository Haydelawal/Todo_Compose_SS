package com.example.todo_compose_ss.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo_compose_ss.data.models.TodoTask
import com.example.todo_compose_ss.data.repositories.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MySharedViewModel @Inject constructor(
    private val repository: TodoRepository
): ViewModel() {

    private val _allTasks = MutableStateFlow<List<TodoTask>>(emptyList())
    val allTasks: StateFlow<List<TodoTask>> = _allTasks

    fun getAllTasks(){
        viewModelScope.launch {
            repository.getAllTasks.collect {
                _allTasks.value = it
            }
        }
    }


//    val searchAppBarState: MutableState<SearchAppBarState> =
//        mutableStateOf(SearchAppBarState.CLOSED)
//    val searchTextState: MutableState<String> = mutableStateOf("")
//
//    private val _allTasks =
//        MutableStateFlow<RequestState<List<ToDoTask>>>(RequestState.Idle)
//    val allTasks: StateFlow<RequestState<List<ToDoTask>>> = _allTasks
//
//    fun getAllTasks() {
//        _allTasks.value = RequestState.Loading
//        try {
//            viewModelScope.launch {
//                repository.getAllTasks.collect {
//                    _allTasks.value = RequestState.Success(it)
//                }
//            }
//        } catch (e: Exception) {
//            _allTasks.value = RequestState.Error(e)
//        }
//    }

}