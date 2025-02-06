package com.example.githubnavigator.domain.profile

interface ProfileRepository {
    suspend fun getProfile(username: String): ProfileDomainEntity?
    suspend fun updateProfile(profile: ProfileDomainEntity)
}