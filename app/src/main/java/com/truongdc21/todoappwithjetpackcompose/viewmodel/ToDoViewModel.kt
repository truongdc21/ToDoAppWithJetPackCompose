package com.truongdc21.todoappwithjetpackcompose.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.truongdc21.todoappwithjetpackcompose.model.ToDo

class ToDoViewModel : ViewModel() {
    val todoList = mutableStateListOf(
        ToDo(1, "Task 1", true),
        ToDo(2, "Task 2", false),
        ToDo(3, "Task 3", true)
    )
}