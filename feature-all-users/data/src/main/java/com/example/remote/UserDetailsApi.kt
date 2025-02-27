package com.example.remote

import com.example.userDetails.UserDetailsResponse
import com.example.userDetails.UserRepoResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface UserDetailsApi {
    @GET("users/{username}")
    suspend fun getUserDetails(
        @Path("username") username: String,
    ): UserDetailsResponse

    @GET("users/{username}/repos")
    suspend fun getUserPublicRepos(
        @Path("username") username: String,
    ): List<UserRepoResponse>
}