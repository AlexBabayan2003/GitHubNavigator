package com.example.database_all_users

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "all_users_table")
data class AllUsersRoomEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val username: String,
    val avatarUrl: String?,
)
