package com.example.githubnavigator.data.login.remote

import com.example.githubnavigator.data.login.remote.model.UserResponse
import retrofit2.http.GET

interface GithubApiService {
    @GET("user")
    suspend fun getUser(): UserResponse
}
