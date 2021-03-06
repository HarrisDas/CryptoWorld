package com.harris.cryptoworld.presentation.cryptolist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.harris.cryptoworld.presentation.CryptoViewModel
import com.harris.cryptoworld.presentation.Screen
import com.harris.cryptoworld.presentation.components.CryptoListItem
import com.harris.cryptoworld.presentation.ui.UIState

@Composable
fun CryptoListScreen(
    navController: NavController,
    viewModel: CryptoViewModel
) {
    val state by viewModel.cryptoList.observeAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        SwipeRefresh(state = SwipeRefreshState(false), onRefresh = { viewModel.getAllCrypto() }) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                (state as? UIState.DataState)?.data?.let {

                    items(it) { crypto ->
                        CryptoListItem(
                            crypto = crypto,
                            onItemClick = {
                                viewModel.selectCrypto(crypto = it)
                                navController.navigate(Screen.CryptoDetailScreen.route)
                            }
                        )
                    }
                }
            }
            when (state) {
                is UIState.ErrorState -> {
                    Text(
                        text = (state as UIState.ErrorState).exception.message ?: "Error",
                        color = MaterialTheme.colors.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .align(Alignment.Center)

                    )

                }
                is UIState.LoadingState -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

                }
            }
        }

    }
}