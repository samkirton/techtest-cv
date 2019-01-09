package com.hsbc.techtest.api

import okhttp3.Interceptor

data class ApiConfig(
    val interceptors: List<Interceptor> = emptyList()
)