package com.example.githubnavigator.domain.userRepos

interface UserReposRepository {
    suspend fun getUserRepos(page: Int, perPage: Int): List<UserReposDomainEntity>
}