package com.example.githubnavigator.domain.profile

data class ProfileEntity(
    val userId: Int,
    val username: String,
    val fullName: String?,
    val bio: String?,
    val avatarLocalUri: String?
)