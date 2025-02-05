package com.example.githubnavigator.data.local


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.githubnavigator.data.allUsers.AllUsersDao
import com.example.githubnavigator.data.allUsers.AllUsersRoomEntity
import com.example.githubnavigator.data.profile.ProfileDao
import com.example.githubnavigator.data.profile.ProfileRoomEntity
import com.example.githubnavigator.data.userRepo.UserReposDao
import com.example.githubnavigator.data.userRepo.UserReposRoomEntity

@Database(
    entities = [
        ProfileRoomEntity::class,
        AllUsersRoomEntity::class,
        UserReposRoomEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao
    abstract fun allUsersDao(): AllUsersDao
    abstract fun userReposDao(): UserReposDao
}