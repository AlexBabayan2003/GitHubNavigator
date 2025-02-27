package com.example.domain.userDetails


interface UserDetailsRepository {
    suspend fun getUserDetails(username: String): UserDetailsDomainEntity
    suspend fun getUserRepos(username: String): List<UserRepoDomainEntity>
}