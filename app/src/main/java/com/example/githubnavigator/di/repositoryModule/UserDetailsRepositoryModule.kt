package com.example.githubnavigator.di.repositoryModule

import com.example.githubnavigator.data.userDetails.UserDetailsRepositoryImpl
import com.example.githubnavigator.domain.userDetails.UserDetailsRepository
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
        userDetailsRepositoryImpl: UserDetailsRepositoryImpl
    ): UserDetailsRepository
}
