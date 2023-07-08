package com.example.todo_compose_ss.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todo_compose_ss.data.dao.TodoDao
import com.example.todo_compose_ss.data.models.TodoTask


@Database(entities = [TodoTask::class], version = 1, exportSchema = false)
abstract class TodoDatabase: RoomDatabase()  {

    abstract fun todoDao(): TodoDao

}