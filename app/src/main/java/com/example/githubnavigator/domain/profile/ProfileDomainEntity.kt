package com.example.githubnavigator.domain.profile

data class ProfileDomainEntity(
    val userId: Int,
    val username: String,
    val fullName: String?,
    val bio: String?,
    val avatarLocalUri: String?
)