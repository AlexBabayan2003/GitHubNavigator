package com.example.userDetails

import com.example.core.di.IoDispatcher
import com.example.domain.userDetails.UserDetailsDomainEntity
import com.example.domain.userDetails.UserDetailsRepository
import com.example.domain.userDetails.UserRepoDomainEntity
import com.example.remote.UserDetailsApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserDetailsRepositoryImpl @Inject constructor(
    private val githubApiService: UserDetailsApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher

) : UserDetailsRepository {
    override suspend fun getUserDetails(username: String): UserDetailsDomainEntity =
        withContext(ioDispatcher) {
            val response = githubApiService.getUserDetails(username)
            UserDetailsMapper.fromResponse(response)
        }

    override suspend fun getUserRepos(username: String): List<UserRepoDomainEntity> =
        withContext(Dispatchers.IO) {
            val response = githubApiService.getUserPublicRepos(username)
            response.map { UserRepoMapper.fromResponse(it) }
        }
}