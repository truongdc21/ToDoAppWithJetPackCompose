package com.truongdc21.todoappwithjetpackcompose.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.truongdc21.todoappwithjetpackcompose.utils.Constant
import com.truongdc21.todoappwithjetpackcompose.R
import com.truongdc21.todoappwithjetpackcompose.model.ToDo
import com.truongdc21.todoappwithjetpackcompose.viewmodel.ToDoViewModel

@SuppressLint("MutableCollectionMutableState")
@Composable
fun ToDoListScreen(navController: NavHostController, viewModel: ToDoViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize(1f)
            .background(Color.DarkGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        DefaultScreen(navController = navController, viewModel)
    }
}

@Composable
private fun DefaultScreen(
    navController: NavHostController,
    mViewModel: ToDoViewModel
) {
    val todoList = mViewModel.mListToDoObserve.observeAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        StatusBar {
            navController.navigate(Constant.ADD_TODO_SCREEN)
        }
        LazyColumn() {
            todoList.value?.let { mList ->
                items(mList) { todo ->
                    ItemToDo(todo = todo, onTapChecked = {
                        mViewModel.updateTask(todo = todo)
                    })
                }
            }
        }
    }
}

@Composable
private fun StatusBar(onClickAddToDo: () -> Unit) {
    Column {
        Row(
            Modifier
                .background(Color.White)
                .padding(10.dp)
        ) {
            Text(
                text = stringResource(id = R.string.to_do_list),
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.Center,
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )

            ImgAddTodo(
                modifier = Modifier
                    .clickable(onClick = onClickAddToDo)
                    .height(30.dp)
                    .width(30.dp)
                    .align(Alignment.CenterVertically)
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
private fun ImgAddTodo(modifier: Modifier) {
    Image(
        imageVector = Icons.Filled.Add,
        contentDescription = null,
        modifier = modifier
    )
}

@Composable
private fun ItemToDo(todo: ToDo, onTapChecked: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .background(Color.White)
            .border(1.dp, Color.DarkGray, shape = RoundedCornerShape(8.dp))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = todo.title,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )
        Checkbox(checked = todo.isCompleted, onCheckedChange = { isChecked ->
            onTapChecked(isChecked)
        }, Modifier.align(Alignment.Top))
    }
}


@Composable
@Preview(
    showSystemUi = true,
    showBackground = true
)
private fun PreViewToDoListScreen() {
    val navHostController = rememberNavController()
    Column() {
        DefaultScreen(navHostController, viewModel())
    }
}
