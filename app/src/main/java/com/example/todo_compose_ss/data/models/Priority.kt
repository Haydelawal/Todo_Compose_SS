package com.example.todo_compose_ss.data.models


import androidx.compose.ui.graphics.Color
import com.example.todo_compose_ss.ui.theme.HighPriorityColor
import com.example.todo_compose_ss.ui.theme.LowPriorityColor
import com.example.todo_compose_ss.ui.theme.MediumPriorityColor
import com.example.todo_compose_ss.ui.theme.NonePriorityColor

enum class Priority(val color: Color) {

    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)

}