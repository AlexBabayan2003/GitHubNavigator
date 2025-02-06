package com.example.githubnavigator.domain.allUsers


data class UserEntityDomain(
    val id: Int,
    val username: String,
    val avatarUrl: String?,
)