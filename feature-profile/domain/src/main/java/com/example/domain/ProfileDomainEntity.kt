package com.example.domain

data class ProfileDomainEntity(
    val userId: Int,
    val username: String,
    val fullName: String?,
    val bio: String?,
    val avatarLocalUri: String?
)