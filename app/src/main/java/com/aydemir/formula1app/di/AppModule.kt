package com.aydemir.formula1app.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.aydemir.formula1app.util.FORMULA1APP_SHARED_PREF_KEY
import com.aydemir.formula1app.util.helper.FavoriteHelper
import com.aydemir.formula1app.util.helper.SharedHelper
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class AppModule {
    /**
     * Provides Application Context
     */
    @Singleton
    @Provides
    fun provideFavoriteHelper(sharedHelper: SharedHelper): FavoriteHelper {
        return FavoriteHelper(sharedHelper)
    }

    /**
     * Provides Application Context
     */
    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    /**
     * Provides [Gson] instance
     */
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    /**
     * Provides [SharedHelper] instance
     */
    @Singleton
    @Provides
    fun provideSharedHelper(sharedPreferences: SharedPreferences, gSon: Gson): SharedHelper {
        return SharedHelper(sharedPreferences, gSon)
    }

    /**
     * Provides Shared Preference instance
     */
    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(FORMULA1APP_SHARED_PREF_KEY, Context.MODE_PRIVATE)
    }

    /**
     * Provide [CompositeDisposable] instance
     */
    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }
}