package com.example.githubnavigator.data.login

import android.util.Log
import com.example.githubnavigator.data.login.remote.GithubApiService
import com.example.githubnavigator.domain.login.AuthResult
import com.example.githubnavigator.domain.login.UserRepository
import com.example.githubnavigator.data.login.local.UserPreferences
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val githubApiService: GithubApiService,
    private val userPreferences: UserPreferences
) : UserRepository {

    /**
     * Attempts to "log in" by calling the GitHub API with the given token.
     * This method uses the "Bearer" approach in the header for verification.
     */
    override suspend fun login(username: String, token: String): AuthResult {
        return try {
            // Save to prefs so interceptor can pick it up
            userPreferences.saveCredentials(username, token)
            // Make the request (no param needed)
            val userResponse = githubApiService.getUser()
            Log.d("SAVED_PREFS", "Saved username: $username, token: $token")
            AuthResult.Success(userResponse)
        } catch (e: Exception) {
            AuthResult.Error("Неверные учетные данные: ${e.message}")
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
