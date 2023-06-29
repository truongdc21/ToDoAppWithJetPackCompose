package com.truongdc21.todoappwithjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.truongdc21.todoappwithjetpackcompose.screen.AddToDoScreen
import com.truongdc21.todoappwithjetpackcompose.screen.ToDoListScreen
import com.truongdc21.todoappwithjetpackcompose.ui.theme.ToDoAppWithJetPackComposeTheme
import com.truongdc21.todoappwithjetpackcompose.utils.Constant
import com.truongdc21.todoappwithjetpackcompose.viewmodel.ToDoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val toDoViewModel by viewModels<ToDoViewModel>()
        setContent {
            val scaffoldState = rememberScaffoldState()
            val snackBarHostState = remember { SnackbarHostState() }
            Scaffold(
                scaffoldState = scaffoldState,
                snackbarHost = {
                    SnackbarHost(hostState = snackBarHostState)
                },
                topBar = {},
                content = {
                    ToDoAppWithJetPackComposeTheme {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colors.background
                        ) {
                            val navHostController = rememberNavController()
                            MyNavHost(navHostController, toDoViewModel)
                        }
                    }
                },
                floatingActionButton = {},
                drawerContent = {},
                bottomBar = {}
            )
        }
    }
}

@Composable
fun MyNavHost(navHostController: NavHostController, toDoViewModel: ToDoViewModel) {
    NavHost(navController = navHostController, startDestination = Constant.TODO_LIST_SCREEN) {
        composable(Constant.TODO_LIST_SCREEN) {
            ToDoListScreen(navController = navHostController, toDoViewModel)
        }
        composable(Constant.ADD_TODO_SCREEN) {
            AddToDoScreen(navController = navHostController, toDoViewModel)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    ToDoAppWithJetPackComposeTheme {
    }
}

