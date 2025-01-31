package com.example.githubnavigator.domain.login

interface UserRepository {
    suspend fun login(username: String, token: String): AuthResult
    fun getToken(): String?
    fun isUserLoggedIn(): Boolean
}
