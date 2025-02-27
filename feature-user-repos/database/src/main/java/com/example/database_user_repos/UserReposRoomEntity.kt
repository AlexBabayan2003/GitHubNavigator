package com.example.database_user_repos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_repos")
data class UserReposRoomEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    )