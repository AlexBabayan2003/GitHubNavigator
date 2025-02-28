package com.example.data

import com.example.core.di.IoDispatcher
import com.example.database_all_users.AllUsersDao
import com.example.domain.AllUsersRepository
import com.example.domain.User
import com.example.remote.AllUsersApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AllUsersRepositoryImpl @Inject constructor(
    private val allUsersApi: AllUsersApi,
    private val allUsersDao: AllUsersDao,
    private val userMapper: UserMapper,
    private val userResponseDomainMapper: UserResponseDomainMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : AllUsersRepository {

    override suspend fun getAllUsers(since: Int): Result<List<User>> =
        runCatching {
            withContext(ioDispatcher) {
                val cachedUsers = allUsersDao.getAllUsers(since)
                if (cachedUsers.isNotEmpty()) {
                    return@withContext cachedUsers.map { userEntity ->
                        userMapper.toDomain(
                            userEntity
                        )
                    }
                } else {
                    val response = allUsersApi.getAllUsers(since)
                    val users = response.map { userResponseDomainMapper.fromResponse(it) }
                    val usersEntity = users.map { userMapper.fromDomain(it) }
                    allUsersDao.insertUsers(usersEntity)
                    return@withContext users
                }
            }
        }
}