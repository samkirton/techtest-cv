package com.hsbc.techtest

import android.app.Application
import androidx.test.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.hsbc.techtest.api.ApiConfig
import com.hsbc.techtest.app.Stub
import com.hsbc.techtest.app.StubInterceptor
import com.hsbc.techtest.app.cv.CvActivity
import java.util.*

class StubActivityTestRule : ActivityTestRule<CvActivity>(
    CvActivity::class.java,
    true,
    false
) {

    private lateinit var stub: Stub

    fun launch(stub: Stub) {
        this.stub = stub
        launchActivity(null)
    }

    private fun inject() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as Application
        val injector = DaggerAppComponent
            .builder()
            .apiConfig(ApiConfig(Arrays.asList(StubInterceptor(stub, context))))
            .application(context)
            .build()

        TestInjectorApplication.setInjector(injector)
    }

    override fun beforeActivityLaunched() {
        super.beforeActivityLaunched()
        inject()
    }

    override fun afterActivityFinished() {
        super.afterActivityFinished()
        TestInjectorApplication.resetInjector()
    }
}