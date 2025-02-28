package com.example.data

import androidx.room.withTransaction
import com.example.database.AppDatabase
import com.example.core.data.local.UserPreferences
import com.example.core.di.IoDispatcher
import com.example.data.remote.UserApi
import com.example.database_all_users.AllUsersDao
import com.example.database_profile.ProfileDao
import com.example.database_user_repos.UserReposDao
import com.example.domain.Profile
import com.example.domain.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val githubApiService: UserApi,
    private val userPreferences: UserPreferences,
    private val profileDao: ProfileDao,
    private val userReposDao: UserReposDao,
    private val database: AppDatabase,
    private val allUsersDao: AllUsersDao,
    private val profileRepository: com.example.domain.ProfileRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : UserRepository {

    override suspend fun login(username: String, token: String): Result<Any> = withContext(ioDispatcher) {
        runCatching {
            // Save initial credentials
            userPreferences.saveCredentials(username, token)
            // Retrieve user info from the API
            val userResponse = githubApiService.getUser()
            // Update credentials with verified username
            userPreferences.saveCredentials(userResponse.username, token)

            val profile = Profile(
                userId = userResponse.id,
                username = userResponse.username,
                fullName = userResponse.fullName,
                bio = userResponse.bio,
                avatarLocalUri = null
            )
            profileRepository.updateProfile(profile)
            // Return the user response on success
            userResponse
        }
    }

    override fun isUserLoggedIn(): Boolean {
        val username = userPreferences.getUsername()
        val token = userPreferences.getToken()
        return !username.isNullOrEmpty() && !token.isNullOrEmpty()
    }

    override suspend fun logout() {
        withContext(ioDispatcher) {
            database.withTransaction {
                userPreferences.clearCredentials()
                profileDao.deleteProfile()
                userReposDao.deleteAllRepos()
                allUsersDao.deleteAllUsers()
            }
        }
    }

    override fun getToken(): String? = userPreferences.getToken()
}
