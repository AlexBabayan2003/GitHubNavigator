package com.example.data.remote

import com.example.core.data.remote.UserResponse
import retrofit2.http.GET

interface UserApi {

    @GET("user")
    suspend fun getUser(): UserResponse

}