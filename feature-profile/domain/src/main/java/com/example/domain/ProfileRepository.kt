package com.example.domain

interface ProfileRepository {
    suspend fun getProfile(username: String): Profile?
    suspend fun updateProfile(profile: Profile)
}