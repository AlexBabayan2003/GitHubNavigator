package com.example.domain_user_repos

interface UserReposRepository {
    suspend fun getUserRepos(page: Int, perPage: Int): List<UserRepos>
}