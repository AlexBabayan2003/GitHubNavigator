package com.example.data_user_repos

import com.example.domain_user_repos.UserRepos
import javax.inject.Inject


class UserReposMapper @Inject constructor() {

    fun fromResponse(response: UserReposResponse): com.example.database_user_repos.UserReposRoomEntity {
        return com.example.database_user_repos.UserReposRoomEntity(
            id = response.id,
            name = response.name
        )
    }

    fun toDomain(roomEntity: com.example.database_user_repos.UserReposRoomEntity): UserRepos {
        return UserRepos(
            id = roomEntity.id,
            name = roomEntity.name
        )
    }
}