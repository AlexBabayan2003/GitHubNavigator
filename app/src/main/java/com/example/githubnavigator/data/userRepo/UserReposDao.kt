package com.example.githubnavigator.data.userRepo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserReposDao {

    @Query("SELECT * FROM user_repos")
    suspend fun getAllRepos():List<UserReposRoomEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<UserReposRoomEntity>)

    @Query("DELETE FROM user_repos")
    suspend fun deleteAllRepos()

}