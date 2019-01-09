package com.hsbc.techtest.uikit

import androidx.annotation.IdRes

data class Interaction<T>(@IdRes val id: Int, val data: T)