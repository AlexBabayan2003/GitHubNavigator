package com.example.githubnavigator.data.profile


import com.example.githubnavigator.di.IoDispatcher
import com.example.githubnavigator.domain.profile.ProfileDomainEntity
import com.example.githubnavigator.domain.profile.ProfileRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
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
        withContext(Dispatchers.IO) {
            profileDao.insertProfile(ProfileMapper.fromDomain(profile))
        }
}
