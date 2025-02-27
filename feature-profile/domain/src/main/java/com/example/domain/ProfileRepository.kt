package com.example.domain

interface ProfileRepository {
    suspend fun getProfile(username: String): ProfileDomainEntity?
    suspend fun updateProfile(profile: ProfileDomainEntity)
}