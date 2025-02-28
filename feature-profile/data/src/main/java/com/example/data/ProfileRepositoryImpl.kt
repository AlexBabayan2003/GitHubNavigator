package com.example.data

import com.example.domain.ProfileRepository
import com.example.core.di.IoDispatcher
import com.example.database_profile.ProfileDao
import com.example.domain.Profile
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileDao: ProfileDao,
    private val profileMapper: ProfileMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ProfileRepository {

    override suspend fun getProfile(username: String): Profile? =
        withContext(ioDispatcher) {
            val profileRoomEntity = profileDao.getProfileByUsername(username)
            profileRoomEntity?.let { profileMapper.toDomain(it) }
        }

    override suspend fun updateProfile(profile: Profile) =
        withContext(ioDispatcher) {
            profileDao.insertProfile(profileMapper.fromDomain(profile))
        }
}
