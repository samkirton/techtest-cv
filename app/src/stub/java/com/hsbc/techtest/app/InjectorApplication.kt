package com.hsbc.techtest.app

import com.hsbc.techtest.api.ApiConfig
import java.util.Arrays.asList

class InjectorApplication : BaseInjectorApplication() {

    override val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .apiConfig(ApiConfig(asList(StubInterceptor(Stub.Success, this))))
            .application(this)
            .build()
    }
}