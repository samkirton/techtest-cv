package com.hsbc.techtest.app

import com.hsbc.techtest.api.ApiConfig

class InjectorApplication : BaseInjectorApplication() {

    override val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .apiConfig(ApiConfig())
            .application(this)
            .build()
    }
}