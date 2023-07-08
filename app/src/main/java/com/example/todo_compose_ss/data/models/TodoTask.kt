package com.example.todo_compose_ss.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todo_compose_ss.utils.Constants.DATABASE_TABLE_NAME

@Entity(tableName = DATABASE_TABLE_NAME)
data class TodoTask(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val priority: Priority
)
