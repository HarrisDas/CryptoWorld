package com.harris.mygroceryshop.data.exception

class AuthenticationException(authMessage: String) :
    NetworkErrorException(errorMessage = authMessage) {}