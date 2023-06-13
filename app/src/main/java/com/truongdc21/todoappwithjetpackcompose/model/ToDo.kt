package com.truongdc21.todoappwithjetpackcompose.model

data class ToDo(
    val id: Int,
    val title: String,
    var isCompleted: Boolean
)