package com.aydemir.formula1app.di

import com.aydemir.formula1app.di.scope.FragmentScope
import com.aydemir.formula1app.view.detail.DetailFragment
import com.aydemir.formula1app.view.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingsModule {

    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun homeFragmentInjector(): HomeFragment

    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun detailFragmentInjector(): DetailFragment
}