package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database_all_users.AllUsersDao
import com.example.database_all_users.AllUsersRoomEntity
import com.example.database_profile.ProfileDao
import com.example.database_profile.ProfileRoomEntity
import com.example.database_user_repos.UserReposDao
import com.example.database_user_repos.UserReposRoomEntity


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
