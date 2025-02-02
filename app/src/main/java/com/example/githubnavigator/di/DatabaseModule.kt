package com.example.githubnavigator.di

import android.content.Context
import androidx.room.Room
import com.example.githubnavigator.data.profile.ProfileDao
import com.example.githubnavigator.data.profile.ProfileDatabase
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
    fun provideProfileDatabase(
        @ApplicationContext context: Context
    ): ProfileDatabase {
        return Room.databaseBuilder(
            context,
            ProfileDatabase::class.java,
            "profile_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideProfileDao(db: ProfileDatabase): ProfileDao {
        return db.profileDao()
    }
}