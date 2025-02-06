package com.example.githubnavigator.data.allUsers

import com.example.githubnavigator.data.remote.GithubApiService
import com.example.githubnavigator.domain.allUsers.AllUsersRepository
import com.example.githubnavigator.domain.allUsers.UserEntityDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AllUsersRepositoryImpl @Inject constructor(
    private val allUsersApi: GithubApiService,
    private val allUsersDao: AllUsersDao,
) : AllUsersRepository {

    override suspend fun getAllUsers(since: Int): List<UserEntityDomain> = withContext(Dispatchers.IO) {
        val cachedUsers = allUsersDao.getAllUsers()
        if (cachedUsers.isNotEmpty()) {
            cachedUsers.map { UserMapper.toDomain(it) }
        } else {
            val response = allUsersApi.getAllUsers(since)
            val users = response.map { UserMapper.fromResponse(it) }
            allUsersDao.insertUsers(users)
            users.map { UserMapper.toDomain(it) }
        }
    }

}

