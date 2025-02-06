package com.example.githubnavigator.domain.userDetails


interface UserDetailsRepository {
    suspend fun getUserDetails(username: String): UserDetailsDomainEntity
    suspend fun getUserRepos(username: String): List<UserRepoDomainEntity>
}