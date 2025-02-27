package com.example.data_user_repos

import android.util.Log
import com.example.core.di.IoDispatcher
import com.example.data_user_repos.remote.UserReposApi
import com.example.database_user_repos.UserReposDao
import com.example.domain_user_repos.UserReposDomainEntity
import com.example.domain_user_repos.UserReposRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class UserReposRepositoryImpl @Inject constructor(
    private val userReposDao: UserReposDao,
    private val githubApiService: UserReposApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : UserReposRepository {
    override suspend fun getUserRepos(page: Int, perPage: Int): List<UserReposDomainEntity> =
        withContext(ioDispatcher) {
            try {
                val cachedRepos = userReposDao.getAllRepos()
                if (cachedRepos.isNotEmpty() && page == 1) {
                    Log.d("UserRepositoryImpl", "Returning cached data")
                    cachedRepos.map { UserReposMapper.toDomain(it) }
                } else {
                    Log.d("UserRepositoryImpl", "Fetching data from network")
                    val response = githubApiService.getUserRepos(page, perPage)
                    val repos = response.map { UserReposMapper.fromResponse(it) }
                    if (page == 1) {
                        userReposDao.deleteAllRepos()
                    }
                    userReposDao.insertAll(repos)
                    repos.map { UserReposMapper.toDomain(it) }
                }
            } catch (e: IOException) {
                Log.e("UserRepositoryImpl", "Network error: ${e.message}")
                val cachedRepos = userReposDao.getAllRepos()
                if (cachedRepos.isNotEmpty()) {
                    Log.d("UserRepositoryImpl", "Returning cached data due to network error")
                    cachedRepos.map { UserReposMapper.toDomain(it) }
                } else {
                    Log.e("UserRepositoryImpl", "No cached data available")
                    throw Exception("Network error and no cached data available", e)
                }
            }
        }
}