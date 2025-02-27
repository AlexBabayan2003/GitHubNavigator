package com.example.data

import com.example.core.di.IoDispatcher
import com.example.database_all_users.AllUsersDao
import com.example.domain.AllUsersRepository
import com.example.domain.UserResponseDomain
import com.example.remote.AllUsersApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AllUsersRepositoryImpl @Inject constructor(
    private val allUsersApi: AllUsersApi,
    private val allUsersDao: AllUsersDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : AllUsersRepository {

    override suspend fun getAllUsers(since: Int): List<UserResponseDomain> =
        withContext(ioDispatcher) {
            val cachedUsers = allUsersDao.getAllUsers(since)


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