package com.example.data

import com.example.domain.ProfileRepository
import com.example.core.di.IoDispatcher
import com.example.database_profile.ProfileDao
import com.example.domain.ProfileDomainEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileDao: ProfileDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ProfileRepository {

    override suspend fun getProfile(username: String): ProfileDomainEntity? =
        withContext(ioDispatcher) {
            val profileRoomEntity = profileDao.getProfileByUsername(username)
            profileRoomEntity?.let { ProfileMapper.toDomain(it) }
        }

    override suspend fun updateProfile(profile: ProfileDomainEntity) =
        withContext(ioDispatcher) {
            profileDao.insertProfile(ProfileMapper.fromDomain(profile))
        }
}
