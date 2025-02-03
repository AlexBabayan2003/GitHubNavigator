package com.example.githubnavigator.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApiService {
    @GET("user")
    suspend fun getUser(): UserResponse

    @GET("users")
    suspend fun getAllUsers(@Query("since") since: Int): List<UserResponse>


}
