package com.example.githubnavigator.data.profile

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ProfileRoomEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ProfileDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao
}