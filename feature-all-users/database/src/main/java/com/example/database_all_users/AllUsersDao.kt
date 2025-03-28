package com.example.database_all_users

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AllUsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<AllUsersRoomEntity>)

    @Query("SELECT * FROM all_users_table WHERE id > :since")
    suspend fun getAllUsers(since: Int,): List<AllUsersRoomEntity>

    @Query("DELETE FROM all_users_table")
    suspend fun deleteAllUsers()
}