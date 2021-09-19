package com.harris.cryptoworld.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import com.harris.cryptoworld.domain.UIState
import com.harris.cryptoworld.ui.theme.MyGroceryShopTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel: CryptoViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyGroceryShopTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    app(viewModel)
                }
            }
        }
    }
}

@Composable
fun app(viewModel: CryptoViewModel) {
//    val value: UIState<List<Crypto>> by viewModel.getAllCryptoCurrencies()
//        .collectAsState(UIState.LoadingState)

    val value by viewModel.mliveData.observeAsState()

    when (value) {
        is UIState.LoadingState -> {
            Text(text = "Loading")

        }
        is UIState.DataState -> {
            Text(text = "Crypto list")

        }
        is UIState.ErrorState -> {
            Text(text = (value as UIState.ErrorState).exception.message ?: "Error")

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
    MyGroceryShopTheme {
        Greeting("Android")
    }
}