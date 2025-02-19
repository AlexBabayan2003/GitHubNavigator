package com.example.githubnavigator.data.login

import androidx.room.withTransaction
import com.example.githubnavigator.data.allUsers.AllUsersDao
import com.example.githubnavigator.data.local.AppDatabase
import com.example.githubnavigator.data.profile.ProfileDao
import com.example.githubnavigator.data.remote.GithubApiService
import com.example.githubnavigator.data.userRepo.UserReposDao
import com.example.githubnavigator.di.IoDispatcher
import com.example.githubnavigator.domain.login.AuthResult
import com.example.githubnavigator.domain.login.UserRepository
import com.example.githubnavigator.domain.profile.ProfileDomainEntity
import com.example.githubnavigator.domain.profile.ProfileRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val githubApiService: GithubApiService,
    private val userPreferences: UserPreferences,
    private val profileDao: ProfileDao,
    private val userReposDao: UserReposDao,
    private val database: AppDatabase,
    private val allUsersDao: AllUsersDao,
    private val profileRepository: ProfileRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : UserRepository {

    override suspend fun login(username: String, token: String): AuthResult = withContext(ioDispatcher) {
        return@withContext try {
            userPreferences.saveCredentials(username, token)
            val userResponse = githubApiService.getUser()
            userPreferences.saveCredentials(userResponse.username, token)

            val profile = ProfileDomainEntity(
                userId = userResponse.id,
                username = userResponse.username,
                fullName = userResponse.fullName,
                bio = userResponse.bio,
                avatarLocalUri = null
            )
            profileRepository.updateProfile(profile)

            AuthResult.Success(userResponse)
        } catch (e: Exception) {
            AuthResult.Error("ERROR: ${e.message}")
        }
    }


    override fun isUserLoggedIn(): Boolean {
        val username = userPreferences.getUsername()
        val token = userPreferences.getToken()
        return !username.isNullOrEmpty() && !token.isNullOrEmpty()
    }

    override suspend fun logout() {
        withContext(ioDispatcher) {
            database.withTransaction { // Wrap in a transaction
                userPreferences.clearCredentials()
                profileDao.deleteProfile()
                userReposDao.deleteAllRepos()
                allUsersDao.deleteAllUsers()
            }
        }
    }

    override fun getToken(): String? = userPreferences.getToken()
}
