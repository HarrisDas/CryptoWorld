package com.harris.cryptoworld.presentation.cryptodetail

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harris.cryptoworld.presentation.CryptoViewModel
import com.harris.cryptoworld.presentation.components.CryptoListItem
import com.harris.cryptoworld.presentation.components.autocomplete.AutoCompleteValueSample
import com.harris.cryptoworld.presentation.ui.UIState

@ExperimentalAnimationApi
@Composable
fun CryptoDetailScreen(viewModel: CryptoViewModel) {
    val crypto by viewModel.currentCrypto.observeAsState()
    val cryptoList by viewModel.cryptoList.observeAsState()
    val convertedAmount by viewModel.convertedAmount.observeAsState()
    val enteredAmount by viewModel.enteredAmount.observeAsState()
    val conversionRate by viewModel.conversionRate.observeAsState()
    val conversionError by viewModel.conversionError.observeAsState()

    val suggestions =
        (cryptoList as? UIState.DataState)?.data?.filter { it.name != crypto?.name }?.map {
            it.name
        } ?: emptyList()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            crypto?.let {

                CryptoListItem(crypto = it)
                Spacer(Modifier.height(16.dp))
                AutoCompleteValueSample(items = suggestions) {
                    viewModel.onCurrencySelected(it)
                }
                Spacer(Modifier.height(16.dp))
                Column(
                    Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(.9f),

                        value = enteredAmount.toString(),
                        onValueChange = { query ->
                            viewModel.onAmountChange(query)
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        )
                    )
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = "Conversion Rate: $conversionRate",
                        Modifier.padding(16.dp),
                        fontSize = 22.sp
                    )
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = "Conversion Amount: $convertedAmount",
                        Modifier.padding(16.dp),
                        fontSize = 22.sp
                    )
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = conversionError?.exception?.message ?: "",
                        Modifier.padding(16.dp),
                        color = Color.Red
                    )

                }
            }
        }

    }


}