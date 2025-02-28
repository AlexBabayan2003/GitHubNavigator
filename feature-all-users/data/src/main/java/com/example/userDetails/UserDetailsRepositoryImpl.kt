package com.example.userDetails

import com.example.core.di.IoDispatcher
import com.example.domain.userDetails.UserDetails
import com.example.domain.userDetails.UserDetailsRepository
import com.example.domain.userDetails.UserRepo
import com.example.remote.UserDetailsApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserDetailsRepositoryImpl @Inject constructor(
    private val githubApiService: UserDetailsApi,
    private val userRepoMapper: UserRepoMapper,
    private val userDetailsMapper:UserDetailsMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher

) : UserDetailsRepository {
    override suspend fun getUserDetails(username: String): UserDetails =
        withContext(ioDispatcher) {
            val response = githubApiService.getUserDetails(username)
            userDetailsMapper.fromResponse(response)
        }

    override suspend fun getUserRepos(username: String): List<UserRepo> =
        withContext(Dispatchers.IO) {
            val response = githubApiService.getUserPublicRepos(username)
            response.map { userRepoMapper.fromResponse(it) }
        }
}