package com.example.githubnavigator.data.profile


import android.util.Log
import com.example.githubnavigator.domain.profile.ProfileEntity
import com.example.githubnavigator.domain.profile.ProfileRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileDao: ProfileDao
) : ProfileRepository {

    override suspend fun getProfile(username: String): ProfileEntity? {
        Log.d("ProfileRepoImpl", "Loading profile for username = $username")
        val roomEntity = profileDao.getProfileByUsername(username)
        Log.d("ProfileRepoImpl", "entity = $roomEntity")
        return roomEntity?.let { ProfileMapper.toDomain(it) }
    }

    override suspend fun updateProfile(profile: ProfileEntity) {
        val roomEntity = ProfileMapper.fromDomain(profile)
        profileDao.insertProfile(roomEntity)
    }
}
