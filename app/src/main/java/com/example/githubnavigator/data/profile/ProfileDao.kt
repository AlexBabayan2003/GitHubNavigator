package com.example.githubnavigator.data.profile
import androidx.room.*
@Dao
interface ProfileDao {

    @Query("SELECT * FROM profile_table WHERE username = :username LIMIT 1")
    suspend fun getProfileByUsername(username: String): ProfileRoomEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: ProfileRoomEntity)

    @Update
    suspend fun updateProfile(profile: ProfileRoomEntity)

    @Query("DELETE FROM profile_table")
    suspend fun deleteProfile()

}