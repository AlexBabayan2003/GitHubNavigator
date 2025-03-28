package com.example.di


import com.example.data.ProfileRepositoryImpl
import com.example.domain.ProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ProfileRepositoryModule {

    @Binds
    @Singleton
    fun bindProfileRepository(
        impl: ProfileRepositoryImpl
    ): ProfileRepository
}