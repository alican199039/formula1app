package com.aydemir.formula1app.di

import com.aydemir.formula1app.view.main.MainActivity
import com.aydemir.formula1app.di.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingsModule {

    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun provideMainActivityInjector(): MainActivity
}