package com.hsbc.techtest.app

import com.hsbc.techtest.app.cv.CvActivity
import com.hsbc.techtest.app.cv.CvActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {

    @ContributesAndroidInjector(modules = [CvActivityModule::class])
    internal abstract fun contributeCvActivity(): CvActivity
}