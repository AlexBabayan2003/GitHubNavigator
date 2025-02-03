package com.example.githubnavigator.data.allusers

import com.example.githubnavigator.data.remote.GithubApiService
import com.example.githubnavigator.domain.allUsers.AllUsersRepository
import com.example.githubnavigator.domain.allUsers.UserEntityDomain
import javax.inject.Inject

class AllUsersRepositoryImpl @Inject constructor(
    private val allUsersApi: GithubApiService,
) : AllUsersRepository {

    override suspend fun getAllUsers(since: Int): List<UserEntityDomain> {
        val response = allUsersApi.getAllUsers(since)
        return response.map {
            UserEntityDomain(
                id = it.id,
                username = it.username,
                avatarUrl = it.avatarUrl
            )
        }
    }
}