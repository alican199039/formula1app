package com.aydemir.formula1app.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aydemir.formula1app.di.scope.ViewModelKey
import com.aydemir.formula1app.util.ViewModelFactory
import com.aydemir.formula1app.view.detail.DetailViewModel
import com.aydemir.formula1app.view.home.HomeViewModel
import com.aydemir.formula1app.view.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class ViewModelBindingsModule {

    @Singleton
    @Binds
    abstract fun bindViewModelFactoryModule(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun provideMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun provideHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    internal abstract fun provideDetailViewModel(detailViewModel: DetailViewModel): ViewModel
}