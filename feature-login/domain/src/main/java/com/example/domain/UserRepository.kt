package com.example.domain



interface UserRepository {
    suspend fun login(username: String, token: String): Result<Any>
    fun getToken(): String?
    fun isUserLoggedIn(): Boolean
    suspend fun logout()
}