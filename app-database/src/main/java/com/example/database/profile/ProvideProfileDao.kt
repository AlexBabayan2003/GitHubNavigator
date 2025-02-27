package com.example.database.profile

import com.example.database.AppDatabase
import com.example.database_profile.ProfileDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProvideProfileDao {

    @Provides
    @Singleton
    fun provideProfileDao(db: AppDatabase): ProfileDao {
        return db.profileDao()
    }

}