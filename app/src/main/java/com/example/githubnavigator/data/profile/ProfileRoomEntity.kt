package com.example.githubnavigator.data.profile

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile_table")
data class ProfileRoomEntity(
    @PrimaryKey val userId: Int,
    val username: String,
    val fullName: String?,
    val bio: String?,
    val avatarLocalUri: String?
)