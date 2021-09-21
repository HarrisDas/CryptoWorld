package com.harris.cryptoworld.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.harris.cryptoworld.domain.model.Crypto
import com.harris.cryptoworld.presentation.cryptolist.CryptoListScreen
import com.harris.cryptoworld.presentation.ui.theme.CryptoWorldTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel: CryptoViewModel by viewModels()
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
                    }
                }
            }
        }
    }
}

@Composable
fun app(viewModel: CryptoViewModel) {

    val value by viewModel.cryptoList.observeAsState()
    Column(modifier = Modifier.verticalScroll(ScrollState(0))) {

        when (value) {
            is UIState.LoadingState -> {
                Text(text = "Loading")

            }
            is UIState.DataState -> {
                for (v in (value as UIState.DataState<List<Crypto>>).data) {
                    Text(text = v.name)

                }

            }
            is UIState.ErrorState -> {
                Text(text = (value as UIState.ErrorState).exception.message ?: "Error")

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