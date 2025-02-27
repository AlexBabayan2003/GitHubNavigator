package com.example.database_profile

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile_table")
data class ProfileRoomEntity(
    @PrimaryKey(autoGenerate = true)
    val userId: Int,
    val username: String,
    val fullName: String?,
    val bio: String?,
    val avatarLocalUri: String?
)