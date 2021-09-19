package com.harris.cryptoworld.domain

/**
 * A State class which will represent the state of UI
 * i.e. loading, error, data
 * */
sealed class State<out T> {
    object LoadingState : State<Nothing>()
    data class ErrorState(var exception: Throwable) : State<Nothing>()
    data class DataState<T>(var data: T) : State<T>()
}