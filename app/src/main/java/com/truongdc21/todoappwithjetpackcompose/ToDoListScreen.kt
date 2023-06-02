package com.truongdc21.todoappwithjetpackcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ToDoListScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize(1f)
            .background(Color.DarkGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "To Do List screen ", fontSize = 26.sp, color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.navigate(Constant.ADD_TODO_SCREEN)
        }) {
            Text(text = "Navigate To Add Todo Screen..")
        }
    }
}