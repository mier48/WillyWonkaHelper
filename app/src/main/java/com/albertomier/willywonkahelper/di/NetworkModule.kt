package com.albertomier.willywonkahelper.di

import com.albertomier.willywonkahelper.data.network.AppApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://2q2woep105.execute-api.eu-west-1.amazonaws.com/napptilus/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideAppApiClient(retrofit: Retrofit): AppApiClient {
        return retrofit.create(AppApiClient::class.java)
    }
}