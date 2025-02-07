package com.example.githubnavigator.data.userRepo

import android.util.Log
import com.example.githubnavigator.data.remote.GithubApiService
import com.example.githubnavigator.domain.userRepos.UserReposDomainEntity
import com.example.githubnavigator.domain.userRepos.UserReposRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class UserReposRepositoryImpl @Inject constructor(
    private val userReposDao: UserReposDao,
    private val githubApiService: GithubApiService,
) : UserReposRepository {
    override suspend fun getUserRepos(page: Int, perPage: Int): List<UserReposDomainEntity> =
        withContext(Dispatchers.IO) {
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