package com.hsbc.techtest

import com.hsbc.techtest.api.ApiConfig
import com.hsbc.techtest.app.AppComponent
import com.hsbc.techtest.app.BaseInjectorApplication
import com.hsbc.techtest.app.Stub
import com.hsbc.techtest.app.StubInterceptor
import dagger.android.AndroidInjector
import java.util.Arrays.asList

class TestInjectorApplication : BaseInjectorApplication() {

    override fun onCreate() {
        instance = this
        super.onCreate()
    }

    private fun inject() {
        applicationInjector().inject(this)
    }

    override fun applicationInjector(): AndroidInjector<BaseInjectorApplication> {
        return androidInjector ?: super.applicationInjector()
    }


    override val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .apiConfig(ApiConfig(asList(StubInterceptor(Stub.Success, this))))
            .application(this)
            .build()
    }

    companion object {

        lateinit var instance: TestInjectorApplication
        private var androidInjector: AndroidInjector<BaseInjectorApplication>? = null

        fun setInjector(injector: AndroidInjector<BaseInjectorApplication>) {
            androidInjector = injector
            instance.inject()
        }

        fun resetInjector() {
            androidInjector = null
            instance.inject()
        }
    }
}