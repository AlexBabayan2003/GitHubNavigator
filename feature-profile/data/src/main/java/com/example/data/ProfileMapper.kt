package com.example.data

import com.example.database_profile.ProfileRoomEntity
import com.example.domain.Profile
import javax.inject.Inject


class ProfileMapper @Inject constructor() {

    fun toDomain(roomEntity: ProfileRoomEntity): Profile {
        return Profile(
            userId = roomEntity.userId,
            username = roomEntity.username,
            fullName = roomEntity.fullName,
            bio = roomEntity.bio,
            avatarLocalUri = roomEntity.avatarLocalUri
        )
    }

    fun fromDomain(domainEntity: Profile): ProfileRoomEntity {
        return ProfileRoomEntity(
            userId = domainEntity.userId,
            username = domainEntity.username,
            fullName = domainEntity.fullName,
            bio = domainEntity.bio,
            avatarLocalUri = domainEntity.avatarLocalUri
        )
    }
}