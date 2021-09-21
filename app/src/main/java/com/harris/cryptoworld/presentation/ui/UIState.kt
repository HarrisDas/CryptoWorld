package com.harris.cryptoworld.presentation.ui

/**
 * A State class which will represent the state of UI
 * i.e. loading, error, data
 * */
sealed class UIState<out T> {
    object LoadingState : UIState<Nothing>()
    data class ErrorState(var exception: Throwable) : UIState<Nothing>()
    data class DataState<T>(var data: T) : UIState<T>()
}