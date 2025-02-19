package com.example.githubnavigator.data.userDetails

import com.example.githubnavigator.data.remote.GithubApiService
import com.example.githubnavigator.di.IoDispatcher
import com.example.githubnavigator.domain.userDetails.UserDetailsDomainEntity
import com.example.githubnavigator.domain.userDetails.UserDetailsRepository
import com.example.githubnavigator.domain.userDetails.UserRepoDomainEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserDetailsRepositoryImpl @Inject constructor(
    private val githubApiService: GithubApiService,
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