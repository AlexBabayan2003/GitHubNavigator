package com.example.database.userRepos

import com.example.database.AppDatabase
import com.example.database_user_repos.UserReposDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProvideUserReposDao {
    @Provides
    @Singleton
    fun provideUserReposDao(db: AppDatabase): UserReposDao {
        return db.userReposDao()
    }
}