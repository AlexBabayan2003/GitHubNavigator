package com.example.githubnavigator.di.repositoryModule

import com.example.githubnavigator.data.userRepos.UserReposRepositoryImpl
import com.example.githubnavigator.domain.userRepos.UserReposRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ReposRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindReposRepository(
        userReposRepositoryImpl: UserReposRepositoryImpl
    ): UserReposRepository
}
