package com.example.githubnavigator.di

import android.content.Context
import androidx.room.Room
import com.example.githubnavigator.data.allUsers.AllUsersDao
import com.example.githubnavigator.data.local.AppDatabase
import com.example.githubnavigator.data.profile.ProfileDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_db" // Use a single database name
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideProfileDao(db: AppDatabase): ProfileDao {
        return db.profileDao()
    }

    @Provides
    @Singleton
    fun provideAllUsersDao(db: AppDatabase): AllUsersDao {
        return db.allUsersDao()
    }




}