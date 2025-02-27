package com.example.di

import com.example.remote.AllUsersApi
import com.example.remote.UserDetailsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProvideAllUsersApi {
    @Provides
    @Singleton
    fun provideGetUserApiService(retrofit: Retrofit): AllUsersApi {
        return retrofit.create(AllUsersApi::class.java)
    }
    @Provides
    @Singleton
    fun provideUserDetailsApiService(retrofit: Retrofit): UserDetailsApi {
        return retrofit.create(UserDetailsApi::class.java)
    }
}