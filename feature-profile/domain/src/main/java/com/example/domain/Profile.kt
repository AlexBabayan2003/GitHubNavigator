package com.example.domain

data class Profile(
    val userId: Int,
    val username: String,
    val fullName: String?,
    val bio: String?,
    val avatarLocalUri: String?
)