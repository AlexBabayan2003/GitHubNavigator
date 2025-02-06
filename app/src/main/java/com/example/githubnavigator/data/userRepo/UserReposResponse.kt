package com.example.githubnavigator.data.userRepo

import com.google.gson.annotations.SerializedName

data class UserReposResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
)