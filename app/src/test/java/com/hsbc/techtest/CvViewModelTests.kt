package com.hsbc.techtest

import com.hsbc.techtest.api.Api
import com.hsbc.techtest.api.CvJson
import com.hsbc.techtest.app.cv.CvIntent
import com.hsbc.techtest.app.cv.CvViewModel
import com.hsbc.techtest.app.cv.CvViewState
import com.hsbc.techtest.util.TestRxScheduler
import com.hsbc.techtest.util.get
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import io.reactivex.Single
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.Assert.assertEquals
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import retrofit2.Response

@RunWith(JUnitPlatform::class)
class CvViewModelTests : Spek({

    given("CvViewModel") {

        val api by memoized { mock<Api>() }
        val testRxScheduler by memoized { TestRxScheduler() }
        val viewModel by memoized { CvViewModel(api, testRxScheduler, mock()) }

        on("Retrieved CV successfully") {

            val cvJson = mock<CvJson>()
            val response = mock<Response<CvJson>>() {
                on {
                    isSuccessful
                }.thenReturn(true)

                on {
                    body()
                }.thenReturn(cvJson)
            }

            whenever(api.getCv()).thenReturn(Single.just(response))

            viewModel.processIntents(Observable.just(CvIntent.Init))
            val states = viewModel.states().test()

            it ("should display cv") {
                assertEquals(CvViewState.View.ShowCv(cvJson), states.get(0).view)
            }
        }

        on("Failed to retrieve cv") {

            val response = mock<Response<CvJson>> {
                on {
                    isSuccessful
                }.thenReturn(false)
            }

            whenever(api.getCv()).thenReturn(Single.just(response))

            viewModel.processIntents(Observable.just(CvIntent.Init))
            val states = viewModel.states().test()

            it ("should display an error message") {
                assertEquals(CvViewState.View.OnError, states.get(0).view)
            }
        }
    }
})