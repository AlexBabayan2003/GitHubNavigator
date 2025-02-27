package com.example.userDetails

import com.google.gson.annotations.SerializedName

data class UserRepoResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
)