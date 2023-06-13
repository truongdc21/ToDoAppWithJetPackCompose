package com.truongdc21.todoappwithjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.truongdc21.todoappwithjetpackcompose.ui.theme.ToDoAppWithJetPackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoAppWithJetPackComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navHostController = rememberNavController()
                    MyNavHost(navHostController)
                }
            }
        }
    }
}

@Composable
fun MyNavHost(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Constant.TODO_LIST_SCREEN) {
        composable(Constant.TODO_LIST_SCREEN) {
            ToDoListScreen(navController = navHostController)
        }
        composable(Constant.ADD_TODO_SCREEN) {
            AddToDoScreen(navController = navHostController)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    ToDoAppWithJetPackComposeTheme {
    }
}

