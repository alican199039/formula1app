package com.aydemir.formula1app.di

import android.app.Application
import com.aydemir.formula1app.Formula1App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, ViewModelBindingsModule::class, AppModule::class,
        ApiModule::class, ActivityBindingsModule::class, FragmentBindingsModule::class]
)
interface AppComponent : AndroidInjector<Formula1App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
