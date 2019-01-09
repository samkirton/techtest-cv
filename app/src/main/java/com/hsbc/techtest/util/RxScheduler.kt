package com.hsbc.techtest.util

import io.reactivex.Scheduler

interface RxScheduler {
    fun main(): Scheduler
    fun thread(): Scheduler
}