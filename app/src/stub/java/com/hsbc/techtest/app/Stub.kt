package com.hsbc.techtest.app

sealed class Stub {
    object Success : Stub()
    object GenericError : Stub()
}