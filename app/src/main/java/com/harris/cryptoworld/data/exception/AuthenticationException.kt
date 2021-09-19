package com.harris.cryptoworld.data.exception

class AuthenticationException(authMessage: String) :
    NetworkErrorException(errorMessage = authMessage) {}