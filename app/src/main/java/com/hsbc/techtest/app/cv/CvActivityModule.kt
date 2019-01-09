package com.hsbc.techtest.app.cv

import androidx.lifecycle.ViewModel
import com.hsbc.techtest.app.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CvActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(CvViewModel::class)
    internal abstract fun contributesCvViewModel(viewModel: CvViewModel): ViewModel
}