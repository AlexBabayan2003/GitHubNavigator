package com.example.domain

interface AllUsersRepository {
    suspend fun getAllUsers(since: Int): Result<List<User>>

}