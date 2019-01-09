package com.hsbc.techtest

import com.hsbc.techtest.api.CvJson
import com.hsbc.techtest.app.cv.CvViewLayout
import com.hsbc.techtest.app.cv.CvViewRenderer
import com.hsbc.techtest.app.cv.CvViewState
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
class CvViewRendererTests : Spek({

    given("CvViewRenderer") {

        on("View.OnProgress") {
            val layout: CvViewLayout = mock()
            val renderer = CvViewRenderer()

            renderer.layout(layout, CvViewState(view = CvViewState.View.OnProgress))

            it("layout.showProgress") {
                verify(layout).showProgress()
            }
        }

        on("View.OnError") {
            val layout: CvViewLayout = mock()
            val renderer = CvViewRenderer()

            renderer.layout(layout, CvViewState(view = CvViewState.View.OnError))

            it("layout.onError") {
                verify(layout).showError()
            }
        }

        on("View.ShowCv") {
            val layout: CvViewLayout = mock()
            val renderer = CvViewRenderer()
            val cv = mock<CvJson>()

            renderer.layout(layout, CvViewState(view = CvViewState.View.ShowCv(cv)))

            it("layout.showCv") {
                verify(layout).showCv(cv)
            }
        }
    }
})