package com.example.githubnavigator.data.local


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.githubnavigator.data.allUsers.AllUsersRoomEntity
import com.example.githubnavigator.data.profile.ProfileRoomEntity
import com.example.githubnavigator.data.allUsers.AllUsersDao
import com.example.githubnavigator.data.profile.ProfileDao

@Database(
    entities = [ProfileRoomEntity::class, AllUsersRoomEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao
    abstract fun allUsersDao(): AllUsersDao
}