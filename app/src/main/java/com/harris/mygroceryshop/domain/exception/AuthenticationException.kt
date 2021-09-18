package com.harris.mygroceryshop.domain.exception

class AuthenticationException(authMessage: String) :
    NetworkErrorException(errorMessage = authMessage) {}