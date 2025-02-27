package com.example.data_user_repos

import com.example.domain_user_repos.UserReposDomainEntity


object UserReposMapper {

    fun fromResponse(response: UserReposResponse): com.example.database_user_repos.UserReposRoomEntity {
        return com.example.database_user_repos.UserReposRoomEntity(
            id = response.id,
            name = response.name
        )
    }

    fun toDomain(roomEntity: com.example.database_user_repos.UserReposRoomEntity): UserReposDomainEntity {
        return UserReposDomainEntity(
            id = roomEntity.id,
            name = roomEntity.name
        )
    }
}