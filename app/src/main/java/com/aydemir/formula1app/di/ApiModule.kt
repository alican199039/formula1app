package com.aydemir.formula1app.di

import com.aydemir.formula1app.BuildConfig
import com.aydemir.formula1app.model.remote.ApiRepositoryImp
import com.aydemir.formula1app.model.remote.ApiService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
class ApiModule {

    @Provides
    @Named("BASE_URL")
    fun provideBaseUrl(): String {
        return BuildConfig.BASE_URL
    }

    @Provides
    fun provideGsonBuilder(): GsonBuilder {
        return GsonBuilder()
    }

    @Provides
    fun provideConverterFactory(builder: GsonBuilder): Converter.Factory {
        return GsonConverterFactory.create(builder.create())
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG)
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        else
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE

        return httpLoggingInterceptor
    }

    @Provides
    fun provideOkHttp(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(BuildConfig.TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(BuildConfig.TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(BuildConfig.TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .retryOnConnectionFailure(true)
            .build()
    }

    @Provides
    fun provideRetrofit(
        @Named("BASE_URL") baseUrl: String, okHttpClient: OkHttpClient,
        gsonConverterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideRepositoryImp(apiService: ApiService): ApiRepositoryImp {
        return ApiRepositoryImp(apiService)
    }
}