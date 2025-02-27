package com.example.database.allUsers


import com.example.database.AppDatabase
import com.example.database_all_users.AllUsersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProvideAllUsersDao {
    @Provides
    @Singleton
    fun provideAllUsersDao(db: AppDatabase): AllUsersDao {
        return db.allUsersDao()
    }
}