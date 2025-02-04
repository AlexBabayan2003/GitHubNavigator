package com.example.githubnavigator.di

import com.example.githubnavigator.data.allUsers.AllUsersRepositoryImpl
import com.example.githubnavigator.domain.allUsers.AllUsersRepository
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