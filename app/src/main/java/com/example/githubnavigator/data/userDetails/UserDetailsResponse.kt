package com.example.githubnavigator.data.userDetails

import com.google.gson.annotations.SerializedName

data class UserDetailsResponse(
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("login") val username: String,
)