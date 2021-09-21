package com.harris.cryptoworld.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.harris.cryptoworld.presentation.cryptodetail.CryptoDetailScreen
import com.harris.cryptoworld.presentation.cryptolist.CryptoListScreen
import com.harris.cryptoworld.presentation.ui.theme.CryptoWorldTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel: CryptoViewModel by viewModels()

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoWorldTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CryptoListScreen.route
                    ) {
                        composable(
                            route = Screen.CryptoListScreen.route
                        ) {
                            CryptoListScreen(navController = navController, viewModel = viewModel)
                        }
                        composable(route = Screen.CryptoDetailScreen.route) {
                            CryptoDetailScreen(viewModel)

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CryptoWorldTheme {
        Greeting("Android")
    }
}