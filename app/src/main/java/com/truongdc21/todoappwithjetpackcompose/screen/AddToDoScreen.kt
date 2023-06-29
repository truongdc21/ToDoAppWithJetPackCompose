package com.truongdc21.todoappwithjetpackcompose.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.truongdc21.todoappwithjetpackcompose.R
import com.truongdc21.todoappwithjetpackcompose.viewmodel.ToDoViewModel

@Composable
fun AddToDoScreen(navController: NavHostController, todoViewModel: ToDoViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize(1f)
            .background(Color.DarkGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        DefaultScreen(navController = navController, todoViewModel)
    }
}

@Composable
private fun DefaultScreen(navController: NavHostController, todoViewModel: ToDoViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val context = LocalContext.current
        val srtTaskState = remember {
            mutableStateOf("")
        }
        StatusBar {
            navController.popBackStack()
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(20.dp)
        ) {
            Text(text = "Enter Task", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(value = srtTaskState.value, onValueChange = {
                srtTaskState.value = it
            }, modifier = Modifier.fillMaxWidth(1f))
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    if (todoViewModel.addToDo(srtTaskState.value)) {
                        srtTaskState.value = ""
                        Toast.makeText(context, "Add Success", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth(1f)
            ) {
                Text(text = "Add Task")
            }
        }
    }
}

@Composable
private fun StatusBar(onBackClick: () -> Unit) {
    Column {
        Row(
            Modifier
                .background(Color.White)
                .padding(10.dp)
        ) {
            ImgBackScreen(
                modifier = Modifier
                    .clickable(onClick = onBackClick)
                    .height(30.dp)
                    .width(30.dp)
                    .align(Alignment.CenterVertically)
            )
            Text(
                text = stringResource(id = R.string.add_to_do),
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.Center,
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray)
                .height(1.dp)
        )
    }
}

@Composable
private fun ImgBackScreen(modifier: Modifier) {
    Image(
        imageVector = Icons.Filled.ArrowBack,
        contentDescription = null,
        modifier = modifier
    )
}

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
private fun PreViewAddScreen() {
    Column() {
        DefaultScreen(navController = rememberNavController(), todoViewModel = ToDoViewModel())
    }
}
