package com.harris.cryptoworld.presentation

sealed class Screen(val route: String) {
    object CryptoListScreen : Screen("crypto_list")
    object CryptoDetailScreen : Screen("crypto_detail")
}