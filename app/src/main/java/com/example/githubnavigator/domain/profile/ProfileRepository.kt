package com.example.githubnavigator.domain.profile

interface ProfileRepository {
    suspend fun getProfile(username: String): ProfileEntity?
    suspend fun updateProfile(profile: ProfileEntity)
}