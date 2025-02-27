package com.example.remote


import com.example.core.data.remote.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AllUsersApi {

    @GET("users")
    suspend fun getAllUsers(@Query("since") since: Int): List<UserResponse>


}