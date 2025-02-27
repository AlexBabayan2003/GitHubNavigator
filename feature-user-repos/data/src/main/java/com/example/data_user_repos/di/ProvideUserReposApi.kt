package com.example.data_user_repos.di

import com.example.data_user_repos.remote.UserReposApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProvideUserReposApi {
    @Provides
    @Singleton
    fun provideUserApiService(retrofit: Retrofit): UserReposApi {
        return retrofit.create(UserReposApi::class.java)
    }
}