package com.example.data.di

import com.example.data.remote.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProvideUserApi {
    @Provides
    @Singleton
    fun provideUserApiService(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }
}