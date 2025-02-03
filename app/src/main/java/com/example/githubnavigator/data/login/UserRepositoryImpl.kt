package com.example.githubnavigator.data.login

import com.example.githubnavigator.data.local.UserPreferences
import com.example.githubnavigator.data.remote.GithubApiService
import com.example.githubnavigator.domain.login.AuthResult
import com.example.githubnavigator.domain.login.UserRepository
import com.example.githubnavigator.domain.profile.ProfileEntity
import com.example.githubnavigator.domain.profile.ProfileRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val githubApiService: GithubApiService,
    private val userPreferences: UserPreferences,
    private val profileRepository: ProfileRepository,
) : UserRepository {
    override suspend fun login(username: String, token: String): AuthResult {
        return try {
            userPreferences.saveCredentials(username, token)
            val userResponse = githubApiService.getUser()
            userPreferences.saveCredentials(userResponse.username, token)

            val profile = ProfileEntity(
                userId = userResponse.id,
                username = userResponse.username,
                fullName = userResponse.fullName,
                bio = userResponse.bio,
                avatarLocalUri = null
            )
            profileRepository.updateProfile(profile)

            AuthResult.Success(userResponse)
        } catch (e: Exception) {
            AuthResult.Error("Ошибка: ${e.message}")
        }
    }

    override fun isUserLoggedIn(): Boolean {
        val username = userPreferences.getUsername()
        val token = userPreferences.getToken()
        return !username.isNullOrEmpty() && !token.isNullOrEmpty()
    }

    override fun logout() {
        userPreferences.clearCredentials()
    }


    override fun getToken(): String? = userPreferences.getToken()
}
