package com.hsbc.techtest.app

import android.app.Application
import com.hsbc.techtest.api.ApiConfig
import com.hsbc.techtest.api.ApiModule
import com.hsbc.techtest.util.UtilModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApiModule::class,
        AppModule::class,
        UtilModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseInjectorApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun apiConfig(apiConfig: ApiConfig): Builder

        fun build(): AppComponent
    }
}