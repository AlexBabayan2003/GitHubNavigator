package com.example.di

import com.example.data.AllUsersRepositoryImpl
import com.example.domain.AllUsersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AllUsersRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAllUsersRepository(
        impl: AllUsersRepositoryImpl,
    ): AllUsersRepository
}