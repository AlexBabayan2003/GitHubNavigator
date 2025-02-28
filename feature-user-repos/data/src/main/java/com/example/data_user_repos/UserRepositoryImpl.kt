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
import kotlin.runCatching

class UserReposRepositoryImpl @Inject constructor(
    private val userReposDao: UserReposDao,
    private val githubApiService: UserReposApi,
    private val userReposMapper: UserReposMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : UserReposRepository {
    override suspend fun getUserRepos(page: Int, perPage: Int): Result<List<UserRepos>> =
        withContext(ioDispatcher) {
            runCatching {
                val cachedRepos = userReposDao.getAllRepos()
                if (cachedRepos.isNotEmpty() && page == 1) {
                    // Return cached repos for first page
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
            }.recoverCatching { throwable ->
                // If an IOException occurs, try to provide cached repos
                if (throwable is IOException) {
                    val cachedRepos = userReposDao.getAllRepos()
                    if (cachedRepos.isNotEmpty()) {
                        cachedRepos.map { userReposMapper.toDomain(it) }
                    } else {
                        throw Exception("Network error and no cached data available", throwable)
                    }
                } else {
                    throw throwable
                }
            }
        }
}