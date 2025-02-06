package com.example.githubnavigator.domain.allUsers

interface AllUsersRepository {
    suspend fun getAllUsers(since: Int): List<UserEntityDomain>

}