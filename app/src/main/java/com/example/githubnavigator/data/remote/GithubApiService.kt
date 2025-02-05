package com.example.githubnavigator.data.remote

import com.example.githubnavigator.data.userRepo.UserReposResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApiService {
    @GET("user")
    suspend fun getUser(): UserResponse

    @GET("users")
    suspend fun getAllUsers(@Query("since") since: Int): List<UserResponse>

    @GET("user/repos")
    suspend fun getUserRepos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<UserReposResponse>

}
