package com.example.domain.userDetails


interface UserDetailsRepository {
    suspend fun getUserDetails(username: String): UserDetails
    suspend fun getUserRepos(username: String): List<UserRepo>
}