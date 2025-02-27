package com.example.di

import com.example.domain.userDetails.UserDetailsRepository
import com.example.userDetails.UserDetailsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserDetailsRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDetailsRepository(
        userDetailsRepositoryImpl: UserDetailsRepositoryImpl,
    ): UserDetailsRepository
}
