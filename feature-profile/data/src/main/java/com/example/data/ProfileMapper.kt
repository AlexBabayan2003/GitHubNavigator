package com.example.data

import com.example.database_profile.ProfileRoomEntity
import com.example.domain.ProfileDomainEntity


object ProfileMapper {

    fun toDomain(roomEntity: ProfileRoomEntity): ProfileDomainEntity {
        return ProfileDomainEntity(
            userId = roomEntity.userId,
            username = roomEntity.username,
            fullName = roomEntity.fullName,
            bio = roomEntity.bio,
            avatarLocalUri = roomEntity.avatarLocalUri
        )
    }

    fun fromDomain(domainEntity: ProfileDomainEntity): ProfileRoomEntity {
        return ProfileRoomEntity(
            userId = domainEntity.userId,
            username = domainEntity.username,
            fullName = domainEntity.fullName,
            bio = domainEntity.bio,
            avatarLocalUri = domainEntity.avatarLocalUri
        )
    }
}