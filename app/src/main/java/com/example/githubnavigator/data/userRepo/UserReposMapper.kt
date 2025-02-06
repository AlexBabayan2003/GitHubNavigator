package com.example.githubnavigator.data.userRepo

import com.example.githubnavigator.domain.userRepos.UserReposDomainEntity

object UserReposMapper {
    fun fromResponse(response: UserReposResponse): UserReposRoomEntity {
        return UserReposRoomEntity(
            id = response.id,
            name = response.name
        )
    }

    fun toDomain(roomEntity: UserReposRoomEntity): UserReposDomainEntity {
        return UserReposDomainEntity(
            id = roomEntity.id,
            name = roomEntity.name
        )
    }
}