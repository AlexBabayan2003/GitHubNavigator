package com.example.data_user_repos.di


import com.example.data_user_repos.UserReposRepositoryImpl
import com.example.domain_user_repos.UserReposRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ReposRepositoryModule {

    @Binds
    @Singleton
    fun bindReposRepository(
        userReposRepositoryImpl: UserReposRepositoryImpl,
    ): UserReposRepository
}
