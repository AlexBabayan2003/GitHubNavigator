package com.example.data_user_repos

import com.google.gson.annotations.SerializedName

data class UserReposResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
)