package com.example.githubnavigator.data.allUsers

import com.example.githubnavigator.data.remote.GithubApiService
import com.example.githubnavigator.data.remote.UserResponseDomainMapper
import com.example.githubnavigator.di.IoDispatcher
import com.example.githubnavigator.domain.allUsers.AllUsersRepository
import com.example.githubnavigator.domain.allUsers.UserResponseDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AllUsersRepositoryImpl @Inject constructor(
    private val allUsersApi: GithubApiService,
    private val allUsersDao: AllUsersDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : AllUsersRepository {

    override suspend fun getAllUsers(since: Int): List<UserResponseDomain> = withContext(ioDispatcher) {
        val cachedUsers = allUsersDao.getAllUsers()
        if (cachedUsers.isNotEmpty()) {
            return@withContext cachedUsers.map { userEntity -> UserMapper.toDomain(userEntity) }
        } else {
            val response = allUsersApi.getAllUsers(since)
            val users = response.map { UserResponseDomainMapper.fromResponse(it) }
            val usersEntity = users.map { UserMapper.fromDomain(it) }
            allUsersDao.insertUsers(usersEntity)
            return@withContext users
        }
    }

}

