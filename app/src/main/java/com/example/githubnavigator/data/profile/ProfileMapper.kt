package com.example.githubnavigator.data.profile

import com.example.githubnavigator.data.profile.ProfileRoomEntity
import com.example.githubnavigator.domain.profile.ProfileEntity

object ProfileMapper {

    fun toDomain(roomEntity: ProfileRoomEntity): ProfileEntity {
        return ProfileEntity(
            userId = roomEntity.userId,
            username = roomEntity.username,
            fullName = roomEntity.fullName,
            bio = roomEntity.bio,
            avatarLocalUri = roomEntity.avatarLocalUri
        )
    }

    fun fromDomain(domainEntity: ProfileEntity): ProfileRoomEntity {
        return ProfileRoomEntity(
            userId = domainEntity.userId,
            username = domainEntity.username,
            fullName = domainEntity.fullName,
            bio = domainEntity.bio,
            avatarLocalUri = domainEntity.avatarLocalUri
        )
    }
}