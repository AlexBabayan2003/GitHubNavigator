package com.example.core.data.remote

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("login") val username: String,
    @SerializedName("id") val id: Int,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("name") val fullName: String?,
    @SerializedName("bio") val bio: String?
)