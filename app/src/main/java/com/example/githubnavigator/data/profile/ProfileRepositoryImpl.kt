package com.example.githubnavigator.data.profile


import com.example.githubnavigator.domain.profile.ProfileDomainEntity
import com.example.githubnavigator.domain.profile.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileDao: ProfileDao,
) : ProfileRepository {

    override suspend fun getProfile(username: String): ProfileDomainEntity? =
        withContext(Dispatchers.IO) {
            val profileRoomEntity = profileDao.getProfileByUsername(username)
            profileRoomEntity?.let { ProfileMapper.toDomain(it) }
        }

    override suspend fun updateProfile(profile: ProfileDomainEntity) =
        withContext(Dispatchers.IO) {
            profileDao.insertProfile(ProfileMapper.fromDomain(profile))
        }
}
