package com.example.data_user_repos

import com.example.core.di.IoDispatcher
import com.example.data_user_repos.remote.UserReposApi
import com.example.database_user_repos.UserReposDao
import com.example.domain_user_repos.UserRepos
import com.example.domain_user_repos.UserReposRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class UserReposRepositoryImpl @Inject constructor(
    private val userReposDao: UserReposDao,
    private val githubApiService: UserReposApi,
    private val userReposMapper: UserReposMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : UserReposRepository {
    override suspend fun getUserRepos(page: Int, perPage: Int): List<UserRepos> =
        withContext(ioDispatcher) {
            try {
                val cachedRepos = userReposDao.getAllRepos()
                if (cachedRepos.isNotEmpty() && page == 1) {
                    cachedRepos.map { userReposMapper.toDomain(it) }
                } else {
                    val response = githubApiService.getUserRepos(page, perPage)
                    val repos = response.map { userReposMapper.fromResponse(it) }
                    if (page == 1) {
                        userReposDao.deleteAllRepos()
                    }
                    userReposDao.insertAll(repos)
                    repos.map { userReposMapper.toDomain(it) }
                }
            } catch (e: IOException) {
                val cachedRepos = userReposDao.getAllRepos()
                if (cachedRepos.isNotEmpty()) {
                    cachedRepos.map { userReposMapper.toDomain(it) }
                } else {
                    throw Exception("Network error and no cached data available", e)
                }
            }
        }
}