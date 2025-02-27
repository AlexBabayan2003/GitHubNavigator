package com.example.data_user_repos.remote

import retrofit2.http.Query
import com.example.data_user_repos.UserReposResponse
import retrofit2.http.GET

interface UserReposApi {

    @GET("user/repos")
    suspend fun getUserRepos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<UserReposResponse>

}